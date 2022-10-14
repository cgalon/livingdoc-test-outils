package fr.pe.incub.mescomics.architecture;

import com.tngtech.archunit.core.importer.ImportOption.*;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "fr.pe.incub.mescomics", importOptions = {DoNotIncludeTests.class, DoNotIncludeJars.class})
public class ArchitectureHexagonaleDuDomaineTest {

    @ArchTest
    public static final ArchRule regleDePlacementDesServices = classes().that().haveSimpleNameContaining("Service").should().resideInAPackage("..domaine..").allowEmptyShould(true);

    @ArchTest
    public static final ArchRule regleDePlacementDesControllers = classes().that().haveSimpleNameContaining("Controller").should().resideInAPackage("..api..").allowEmptyShould(true);

    @ArchTest
    public static final ArchRule regleDePlacementDesEntrepots = noClasses().that().haveSimpleNameContaining("Entrepot").should().resideInAPackage("..api..");

    @ArchTest
    public static final ArchRule regleDeDependanceDeLaCoucheDomaine = noClasses().that().resideInAPackage("..domaine..").should().accessClassesThat().resideInAPackage("..infrastructure..");

    @ArchTest
    public static final ArchRule regleDeDependanceDeLaCoucheInterface = noClasses().that().resideInAPackage("..api..").should().accessClassesThat().resideInAPackage("..persistance..").allowEmptyShould(true);

}
