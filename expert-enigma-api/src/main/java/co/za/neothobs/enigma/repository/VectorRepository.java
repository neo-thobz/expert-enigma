package co.za.neothobs.enigma.repository;

import java.util.List;

import co.za.neothobs.enigma.model.DocumentChunk;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VectorRepository extends JpaRepository<DocumentChunk, Long> {

    // Find top N most similar vectors using pgvector cosine similarity
    @Query(value = "SELECT * FROM cv_chunks ORDER BY embedding <=> :vector LIMIT :limit", nativeQuery = true)
    List<DocumentChunk> findTopKSimilar(@Param("vector") float[] vector, @Param("limit") int limit);
}
