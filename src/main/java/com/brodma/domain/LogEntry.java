package com.brodma.domain;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Indexed: indicates that this entity should be indexed by Lucene, making it searchable.
 * @Analyzer: tells Hibernate Search which Lucene analyzer to use when tokenizing its fields and updating the Lucene index
 * @Field: tells Hibernate Search to indexer this field
 * @DocumentId field should be used as the ID of the Lucene documents in the index.
 */

@Indexed
@Analyzer(impl = StandardAnalyzer.class)
@Entity
@Table(catalog = "publishing", schema = "public", name = "logs")
public class LogEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @DocumentId
    @Type(type = "objectid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field
    @Column
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LogEntry{id=" + Objects.toString(id) + ", text=" + Objects.toString(text) + "}";
    }
}
