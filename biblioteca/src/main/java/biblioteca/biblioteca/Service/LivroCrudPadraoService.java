package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Autor autor = obterAutorExistente(livroPostPutDto.getAutorId());
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        livro.setId(null);
        livro.setAutor(autor);
        return paraResponse(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }
 
    public List<LivroResponseDto> buscarLivrosIsbn(String isbn) {
        return livroRepository.findByIsbnContaining(isbn)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public List<LivroResponseDto> buscarLivrosTitulo(String titulo) {
        return livroRepository.findByTituloContaining(titulo)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public List<LivroResponseDto> buscarTodosLivrosIsbnTitulo(String isbn, String titulo) {
        return livroRepository.findByIsbnContainingAndTituloContaining(isbn, titulo)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return paraResponse(obterLivroExistente(id));
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);

        Autor autor = obterAutorExistente(livroPostPutDto.getAutorId());
        livro.setAno(livroPostPutDto.getAno());
        livro.setAutor(autor);
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setIsbn(livroPostPutDto.getIsbn());
        
        return paraResponse(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    // id inexistente = 404
    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long autorId) {
        if (autorId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        }
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    private LivroResponseDto paraResponse(Livro livro) {
        LivroResponseDto dto = modelMapper.map(livro, LivroResponseDto.class);
        if (livro.getAutor() != null) {
            dto.setAutorId(livro.getAutor().getId());
        }
        return dto;
    }
}