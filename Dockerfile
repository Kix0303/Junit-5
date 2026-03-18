# ── STAGE 1 : build ──────────────────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /src

# Copier pom.xml en premier pour profiter du cache Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source (dans le sous-dossier "TP Junit")
COPY ["TP Junit", "./TP Junit"]

# Compiler et créer le JAR (sans relancer les tests)
RUN mvn package -DskipTests -B

# ── STAGE 2 : image de production ────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Récupérer uniquement le JAR produit par le stage 1
COPY --from=build /src/target/boutique.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
