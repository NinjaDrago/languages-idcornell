### `README.md`

```markdown
# Pokémon Hangman Game 
Chat GPT conversation link:
https://chatgpt.com/share/6710805b-1174-8013-a54d-a9d8c4fa3fd2
This is a multi-user Pokémon Hangman game built with **Node.js**, **Express**, and **PostgreSQL**. The application supports user registration, login, and allows multiple users to play individual games simultaneously. The backend uses a PostgreSQL database to keep track of users, game states, and the word list. The frontend is a simple, mobile-friendly interface built using **HTML**, **CSS**, **JavaScript**, and **Bootstrap**.

## Features
- Register and login functionality.
- Start a new game with a random word.
- Submit guesses and track remaining attempts.
- View guessed letters and the state of the word being guessed.
- Admin functionality to manage the list of words in the database.
- Dockerized environment for easy development and deployment.

## Project Structure
```
hangman-app/
├── .devcontainer/           # Dev container configuration for VS Code (optional)
│   └── devcontainer.json
├── db/                      # Database-related files
│   └── init.sql             # SQL script to initialize the database schema
├── public/                  # Static frontend files
│   ├── hangman0.svg         # Hangman image for initial state
│   ├── hangman1.svg         # Hangman image for 1 incorrect guess
│   ├── hangman2.svg         # Hangman image for 2 incorrect guesses
│   ├── hangman3.svg         # Hangman image for 3 incorrect guesses
│   ├── hangman4.svg         # Hangman image for 4 incorrect guesses
│   ├── hangman5.svg         # Hangman image for 5 incorrect guesses
│   ├── hangman6.svg         # Hangman image for 6 incorrect guesses (game over)
│   ├── index.html           # Main HTML file for the frontend
│   ├── script.js            # JavaScript logic for frontend interactions
│   └── style.css            # Custom styles for the frontend
├── .dockerignore            # Ignore rules for Docker
├── .gitignore               # Ignore rules for Git
├── Dockerfile               # Dockerfile for building the Node.js application
├── docker-compose.yml       # Docker Compose configuration file
├── app.js                   # Node.js backend server code
├── package.json             # Node.js dependencies and scripts
├── package-lock.json        # Locked dependencies for Node.js
├── .env                     # Environment variables for local development
└── README.md                # Project documentation (this file)
```

## Prerequisites
Before you begin, ensure you have the following software installed on your machine:

- [Node.js](https://nodejs.org/) (>= v14)
- [Docker](https://www.docker.com/) (for Docker-based deployment)
- [Git](https://git-scm.com/) (for version control)

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/hangman-app.git
cd hangman-app
```

### 2. Set Up Environment Variables
Create a `.env` file in the root directory and add the following configuration:
```
# .env file
DATABASE_URL=postgres://postgres:postgres@db:5432/hangman_db
PORT=3000
```
make sure .env file is inside project folder(ex. inside hangman assigment)

### 3. Running the Application

You have two options to run the application: using **Docker** or running it locally.

### Option 1: Running with Docker
1. **Build and Run the Containers**:
   ```bash
   docker-compose up --build
   ```
   before running command make sure you are in the folder of the project 

2. **Access the Application**:
   - Navigate to `http://localhost:3000` in your browser.
   - The application should now be running with a PostgreSQL database in the background.

### Option 2: Running Locally
1. **Install Dependencies**:
   ```bash
   npm install
   ```

2. **Start the PostgreSQL Database**:
   - Install PostgreSQL locally or use a PostgreSQL server.
   - Create a database named `hangman_db`.
   - Run the SQL commands in `db/init.sql` to set up the database schema.

3. **Start the Application**:
   ```bash
   npm start
   ```

4. **Access the Application**:
   - Navigate to `http://localhost:3000` in your browser.

### 4. Development with Dev Containers (Optional)
If you're using Visual Studio Code and have Docker installed, you can use the dev container setup provided:

1. Open the project in VS Code.
2. Click on the green icon in the bottom-left corner and select **"Reopen in Container"**.
3. VS Code will build and open the dev container with the Node.js environment.

## Endpoints

### User Endpoints
- **POST /register**: Register a new user.
  - Request Body: `{ "username": "your_username" }`
  - Response: `{ "id": 1, "username": "your_username" }`

- **POST /login**: Login an existing user.
  - Request Body: `{ "username": "your_username" }`
  - Response: `{ "id": 1, "username": "your_username" }`

### Game Endpoints
- **POST /start**: Start a new game for a user.
  - Request Body: `{ "userId": 1 }`
  - Response: `{ "gameId": 1, "wordLength": 8 }`

- **POST /guess**: Submit a letter guess for the current game.
  - Request Body: `{ "gameId": 1, "letter": "a" }`
  - Response: `{ "status": "active", "remainingAttempts": 5 }`

- **GET /state**: Get the current state of a game.
  - Request Query: `?gameId=1`
  - Response: `{ "wordState": "_ _ a _ _ _ _ _", "guessedLetters": ["a"], "remainingAttempts": 5, "status": "active" }`

### Admin Endpoints
- **GET /admin/words**: Retrieve all words in the database.
  - Response: `["javascript", "nodejs", "docker", "postgresql"]`

- **POST /admin/words**: Update the word list in the database.
  - Request Body: `{ "words": ["newword1", "newword2", "newword3"] }`
  - Response: `{ "message": "Words updated successfully" }`

## Screenshots
![Hangman Game Screenshot](https://github.com/NinjaDrago/languages-idcornell/tree/main/hangman/ScreenShots)

## License
This project is licensed under the MIT License. Feel free to use, modify, and distribute as needed.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Make sure to follow the coding style and add appropriate tests for any new features.

## Acknowledgments
- [Node.js](https://nodejs.org/)
- [Express](https://expressjs.com/)
- [PostgreSQL](https://www.postgresql.org/)
- [Bootstrap](https://getbootstrap.com/)
- SVG graphics created manually for hangman game states.
```

### How to Use:
1. Place this file in the root of your project directory.
2. Update any missing details, such as your GitHub repository URL or additional instructions.
3. Commit the `README.md` file to your Git repository:

   ```bash
   git add README.md
   git commit -m "Add project documentation"
   git push origin main
   ```
