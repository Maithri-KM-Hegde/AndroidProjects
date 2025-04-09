## News App (Sample Project for Jetpack Compose)

A simple Android App which fetches and displays the US Top news headlines.

## There are 3 layer in this app. Project follows MVVM Clean Code architecture

| Domain Layer         | Presentation Layer | Data Layer                         |
|----------------------|--------------------|------------------------------------|
| usecase              | screens            | data source, dto                   |
| repository interface | viewmodel          | repository implementation          |
|                      |                    | your library config(retrofit/room) |

## Setup Instructions

This app uses **[NewsAPI](https://newsapi.org/)** to fetch news. Generate your own API_KEY
from the website and configure the BASE_URL and API_KEY in local.properties.