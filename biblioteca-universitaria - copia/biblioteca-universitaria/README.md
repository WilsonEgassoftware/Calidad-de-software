# Biblioteca Universitaria - Calidad de Software (Sesión 27)

Proyecto Java con Maven configurado con herramientas de análisis estático y cobertura de código:
- **JUnit 5**: Pruebas unitarias.
- **JaCoCo**: Cobertura de código (`target/site/jacoco/`).
- **Checkstyle**: Estándares de estilo (`target/checkstyle-result.xml`).
- **PMD**: Análisis estático de buenas prácticas y diseño (`target/pmd.xml`).
- **SpotBugs**: Detección de posibles defectos (`target/spotbugsXml.xml`).

## Pipeline CI/CD (GitHub Actions)
El archivo `.github/workflows/ci.yml` ejecuta los 6 jobs requeridos:
1. Compilación Maven
2. Pruebas unitarias JUnit
3. Cobertura JaCoCo
4. Análisis Checkstyle
5. Análisis PMD
6. Análisis SpotBugs
