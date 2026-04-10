# 🌦 Weather App (Java)

## 📌 Descrizione del progetto

Questo progetto consiste in una semplice applicazione meteo sviluppata in Java. L’app consente all’utente di inserire il nome di una città e ottenere informazioni meteorologiche aggiornate in tempo reale.

Il funzionamento si basa sull’utilizzo dell’API di Open-Meteo:

* prima viene effettuata una richiesta di **geocodifica** per convertire il nome della città in coordinate geografiche (latitudine e longitudine)
* successivamente, le coordinate vengono utilizzate per recuperare i dati meteo

L’app mostra:

* 🌡 Temperatura
* 💨 Velocità del vento
* 💧 Umidità
* 🌧 Precipitazioni

I dati vengono visualizzati in modo semplice e leggibile direttamente nel terminale.

---

## 🗂 Struttura del progetto

```text
src/
└── main/java/com/tuo_nome/weatherapp/
    ├── Main.java                # Punto di ingresso dell'app
    ├── service/
    │   └── WeatherService.java  # Logica per chiamate API
    └── model/
        └── WeatherData.java     # Modello dati meteo
```

---

## ▶️ Come eseguire il progetto

### ✅ Prerequisiti

* Java 21 (o superiore)
* Maven
* Visual Studio Code (consigliato)

---

### 🔧 1. Clona il repository

```bash
git clone <url-del-repository>
cd weatherapp
```

---

### ⚙️ 2. Compila il progetto

```bash
mvn clean install
```

---

### ▶️ 3. Esegui l'app

```bash
mvn exec:java -Dexec.mainClass="com.tuo_nome.weatherapp.Main"
```

Oppure direttamente da VS Code:

* apri `Main.java`
* clicca su **Run**

---

## 💻 Utilizzo

Quando avvii il programma, ti verrà richiesto di inserire una città:

```text
Inserisci una città: Roma
```

Output esempio:

```text
🌍 Meteo per Roma:

🌡 Temperatura: 22.5°C
💨 Vento: 10.2 km/h
💧 Umidità: 65%
🌧 Precipitazioni: 0.0 mm
```

---

## ⚠️ Note

* L’app utilizza dati in tempo reale, quindi è necessaria una connessione internet
* Se la città non viene trovata, verrà mostrato un messaggio di errore
* I dati meteo sono forniti dall’API Open-Meteo

---

## 🚀 Possibili miglioramenti

* Interfaccia grafica (JavaFX o Swing)
* Previsioni meteo per più giorni
* Supporto per più lingue
* Miglior gestione degli errori

---
