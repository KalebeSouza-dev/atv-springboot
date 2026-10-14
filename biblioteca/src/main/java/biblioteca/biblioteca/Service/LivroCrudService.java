package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto); 

    List<LivroResponseDto> buscarLivrosByTituloAndIsbn(String isbn, String Titulo);
    
    List<LivroResponseDto> buscarLivrosByIsbn(String isbn);

    List<LivroResponseDto> buscarLivrosByTitulo(String titulo);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarLivro(Long id);

    LivroPostPutDto atualizarLivro(Long id);

    void removerLivro(Long id);

    void removerLivrosByAutor(String autor);
}
