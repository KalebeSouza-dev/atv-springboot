package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto); 

    List<LivroResponseDto> buscarTodosLivros();

    List<LivroResponseDto> buscarLivrosIsbn(String isbn);
    
    List<LivroResponseDto> buscarLivrosTitulo(String titulo);

    List<LivroResponseDto> buscarTodosLivrosIsbnTitulo(String isbn, String Titulo);

    LivroResponseDto buscarLivro(Long id);

    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto);

    void removerLivro(Long id);
}
