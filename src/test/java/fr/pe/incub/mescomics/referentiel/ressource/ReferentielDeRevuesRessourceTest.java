package fr.pe.incub.mescomics.referentiel.ressource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReferentielDeRevuesController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-docs")
public class ReferentielDeRevuesRessourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void doitModifierLeNombreDeNumerosDUneRevue() throws Exception {
        this.mockMvc.perform(put("/revue").param("nouveauNombreDeNumeros", "15"))
                .andExpect(status().isOk())
                .andDo(document("test-spring-restdocs"));
    }
}
