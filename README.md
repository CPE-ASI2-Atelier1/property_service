# 📘 **Property Service - Documentation Technique**

## 📚 **Table des matières**
1. [Introduction](#1-introduction)
2. [Architecture générale](#2-architecture-générale)
3. [Composants principaux](#3-composants-principaux)
   - [1. PropertyServiceApplication](#1-propertyserviceapplication)
   - [2. MicroPropertyController](#2-micropropertycontroller)
   - [3. MicroPropertyService](#3-micropropertyservice)
   - [4. MicroPropertyListener](#4-micropropertylistener)
4. [Fichier de configuration](#4-fichier-de-configuration)
5. [Fonctionnalités](#5-fonctionnalités)
6. [Flux de données](#6-flux-de-données)
7. [Technologies utilisées](#7-technologies-utilisées)
8. [Exécution de l'application](#8-exécution-de-lapplication)
9. [Améliorations possibles](#9-améliorations-possibles)

---

## 📘 **1. Introduction**
Le **Property Service** est une application de microservice qui utilise **Spring Boot** et **ActiveMQ** pour **envoyer et recevoir des messages**.  
L'objectif principal du service est de **transformer des URLs d'images en propriétés calculées** (DEFENSE, ENERGY, HP, ATTACK) via une librairie externe, puis d'envoyer ces propriétés au **bus approprié**.

---

## 📘 **2. Architecture générale**
L'architecture repose sur les concepts suivants :
- **Contrôleur REST** (`MicroPropertyController`) qui reçoit les requêtes POST avec un body de forme json d'un client externe. Le json est de la forme : 
{
  "url": "https://example.com/image.png",
  "busName": "TestBus",
  "cardid": "2",
  "userid": "4"
}
- **Service métier** (`MicroPropertyService`) qui envoie un un sous-ensemble du body d'origine composé de l'url, de la cardid et du userid au **bus ActiveMQ**.
- **Listener JMS** (`MicroPropertyListener`) qui écoute les **queues ActiveMQ** puis qui envoie l'url à la librairie locale. Les propriétés données par la librairie apès traitement sont envoyées en reqûete POST au scheduler service avec les id user et card.

## 📘 **3. Fichier de configuration**
Le fichier de configuration détermine le port 8082 pour l'application et les identifiants pour la queue activeMQ notamment.

## 📘 **4. Flux de données**
- **Client** → /card (via POST)
- **MicroPropertyController** → Envoi d'un message au bus ActiveMQ.
- **ActiveMQ** → Message intercepté par MicroPropertyListener.
- **MicroPropertyListener** → Transformation de l'image via ImgToProperties.
- **MicroPropertyListener** → Envoi des résultats à l'API distante.

## 📘 **5. Reste à faire**
Connecter les services scheduler et property pour avoir un chaîne fonctionnelle.