package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    // (ISBN, Título, Autor, Ano e Edição)

    @JsonProperty("isbn")
    @NotBlank(message = "O ISBN do livro não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "O título do livro não pode ser vazia")
    private String titulo;

    @JsonProperty("Autor")
    @NotBlank(message = "O autor do livro não pode ser vazio")
    private String autor; //casa

    @JsonProperty("ano")
    @NotBlank(message = "O ano do livro não pode ser vazia")
    private int ano;

    @JsonProperty("edicao")
    @NotBlank(message = "A edicao do livro não pode ser vazia")
    private int edicao;
}