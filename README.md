# Calidad-de-software
# Biblioteca Universitaria — Calidad de Software y Análisis Estático

Proyecto Java gestionado con **Maven** e integrado con **GitHub Actions** para automatizar la compilación, la ejecución de pruebas unitarias, la medición de cobertura y el análisis estático de calidad del código[cite: 2, 4].

## Descripción del Proyecto
El proyecto modela la gestión básica de libros y catálogo (`Libro` y `Biblioteca`) junto con sus pruebas unitarias en **JUnit 5**, aplicando buenas prácticas de programación, validaciones de dominio y métricas de calidad verificables en cada ejecución del pipeline[cite: 5, 6, 7, 8].

## Herramientas de Calidad Integradas
* **JUnit 5 y Maven Surefire**: Ejecución automatizada de pruebas unitarias y generación de reportes en `target/surefire-reports/`[cite: 2, 4].
* **JaCoCo**: Medición de cobertura de código y generación de reportes XML/HTML en `target/site/jacoco/`[cite: 2, 4].
* **Checkstyle**: Verificación de convenciones de estilo, formato y documentación Javadoc según `checkstyle.xml` (`target/checkstyle-result.xml`)[cite: 2, 3, 4].
* **PMD**: Análisis estático de buenas prácticas, estilo, diseño y detección de errores comunes (`target/pmd.xml`)[cite: 2, 4].
* **SpotBugs**: Detección de posibles defectos y patrones riesgosos en el bytecode compilado (`target/spotbugsXml.xml`)[cite: 2, 4].

## Pipeline de GitHub Actions
El workflow `.github/workflows/ci.yml` ejecuta de forma automatizada los siguientes **6 jobs**[cite: 2]:
1. **Compilación Maven** (`mvn clean compile`)[cite: 2].
2. **Pruebas unitarias JUnit** (`mvn test`)[cite: 2].
3. **Cobertura JaCoCo** (`mvn test jacoco:report`)[cite: 2].
4. **Análisis Checkstyle** (`mvn checkstyle:checkstyle`)[cite: 2].
5. **Análisis PMD** (`mvn pmd:pmd`)[cite: 2].
6. **Análisis SpotBugs** (`mvn test-compile spotbugs:spotbugs`)[cite: 2].

## Ejecución Local
Asegúrate de tener **JDK 17** y **Maven** instalados en tu entorno local y ejecuta desde la raíz del proyecto[cite: 4]:

```bash
mvn clean test jacoco:report checkstyle:checkstyle pmd:pmd spotbugs:spotbugs
