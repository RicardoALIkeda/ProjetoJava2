package br.dev.joaquim.livroapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    @Column(name="`year`")
    private int year;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private BookCollection collection;
}
