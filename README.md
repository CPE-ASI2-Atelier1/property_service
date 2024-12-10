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
- **Contrôleur REST** (`MicroPropertyController`) qui reçoit les requêtes POST d'un client externe.
- **Service métier** (`MicroPropertyService`) qui envoie un message au **bus ActiveMQ**.
- **Listener JMS** (`MicroPropertyListener`) qui écoute les **queues ActiveMQ**.
- **Processus d'appel externe** à une **API distante** (via RestTemplate) et **exploitation d'une librairie de calculs de propriétés d'image**.

