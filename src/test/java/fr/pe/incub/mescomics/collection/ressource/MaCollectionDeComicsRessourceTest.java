package fr.pe.incub.mescomics.collection.ressource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-docs")
public class MaCollectionDeComicsRessourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void doitSupprimerUneListeDeComicsEnFonctionDUnTitre() throws Exception {
        this.mockMvc.perform(delete("/collection/titre").param("titre", "Strange"))
                .andExpect(status().isOk());
    }
}
