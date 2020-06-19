package br.com.vinicius;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.vinicius");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.vinicius.service..")
            .or()
            .resideInAnyPackage("br.com.vinicius.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.com.vinicius.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
