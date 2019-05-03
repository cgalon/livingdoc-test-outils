package fr.pe.incub.mescomics.referentiel.ressource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-docs")
public class ReferentielDeRevuesRessourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void doitModifierLeNombreDeNumerosDUneRevue() throws Exception {
        this.mockMvc.perform(put("/revue")
                .param("nouveauNombreDeNumeros", "15"))
                .andExpect(status().isOk())
                .andDo(document("test-spring-restdocs"));
    }
}
