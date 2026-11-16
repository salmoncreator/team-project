# Trivia Quiz Program

## Team Information

### Team Members

- Edison Liao
- Eric Zhu
- Simon Kim
- Tokhtar Yessergepov

---

## Trivia Quiz Program

### Domain

The Trivia Quiz Program is an interactive application that allows users to create, customize, and participate in trivia
quizzes. Quiz creators can build quizzes by adding multiple-choice questions, specifying correct answers, and saving
their quizzes for future use.

Quiz takers can enter their names, select a quiz, and answer questions through a user-friendly interface. During a quiz,
participants can track their progress and view their current scores. After completing a quiz, participants receive their
final results.

The application also allows quiz reviewers to view participants' scores and compare their performance.

The goal of this project is to provide an accessible and entertaining platform for testing knowledge while allowing
users to create and manage their own trivia content.

---

## User Stories

### Quiz Taker

1. As a quiz taker, I want to enter my name so that I can compete against other people.

2. As a quiz taker, I want to answer trivia questions through a clean and interactive interface so I can test my
   knowledge in a fun, low-stakes environment.

3. As a quiz taker, I want to see a progress bar so I know how many questions are left.

4. As a quiz taker, I want to see my score so I know how well I am doing.

### Quiz Creator

5. As a quiz creator, I want to use a form to create and edit trivia questions so I can build custom quizzes.

6. As a quiz creator, I want to be able to preview my quiz so I can ensure a good experience for my audience.

7. As a quiz creator, I want to save my questions and quizzes so that I can use them later.

### Quiz Reviewer

8. As a quiz reviewer, I want to view a summary screen showing each participant's score after they complete the quiz.

---

## Use Cases

### Use Case 1: Enter Participant Name

**Actor:** Quiz Taker

**Description:** Allows a participant to enter their name before starting a quiz.

**Main Flow:**

1. The user opens the application.
2. The user enters their name.
3. The application validates the input.
4. The application stores the participant's name for the current quiz session.
5. The user proceeds to quiz selection.

**Expected Outcome:** The participant's name is associated with their quiz results.

### Use Case 2: Take Trivia Quiz

**Actor:** Quiz Taker

**Description:** Allows participants to answer multiple-choice trivia questions.

**Main Flow:**

1. The participant selects an available quiz.
2. The application displays the first question and its answer choices.
3. The participant selects an answer.
4. The application checks whether the answer is correct.
5. The application updates the participant's score and progress bar.
6. The process repeats until all questions have been answered.
7. The application displays the final score.

**Expected Outcome:** The participant completes the quiz and receives their results.

### Use Case 3: Create and Edit Quiz

**Actor:** Quiz Creator

**Description:** Allows users to create and modify custom trivia quizzes.

**Main Flow:**

1. The creator selects the option to create a quiz.
2. The creator enters a quiz title.
3. The creator adds questions using an input form.
4. Each question includes answer choices and a designated correct answer.
5. The creator can edit or remove questions.
6. The creator saves the completed quiz.

**Expected Outcome:** A new or updated quiz is available for participants.

### Use Case 4: Preview Quiz

**Actor:** Quiz Creator

**Description:** Allows creators to preview quizzes before making them available.

**Main Flow:**

1. The creator selects an existing or newly created quiz.
2. The creator clicks the preview option.
3. The application displays the quiz as it would appear to a participant.
4. The creator navigates through the questions.
5. The creator returns to the editing interface to make changes if necessary.

**Expected Outcome:** The creator can verify the quiz's content and presentation before use.

### Use Case 5: Save and Load Quizzes

**Actor:** Quiz Creator

**Description:** Allows users to save quizzes and retrieve them later.

**Main Flow:**

1. The creator finishes creating or editing a quiz.
2. The creator selects the save option.
3. The application stores the quiz and its questions in persistent storage.
4. When the application is reopened, previously saved quizzes are loaded.
5. The creator can select a saved quiz to edit or preview.

**Expected Outcome:** Quizzes remain available between application sessions.

### Use Case 6: View Quiz Results

**Actor:** Quiz Reviewer

**Description:** Allows reviewers to examine participants' quiz performances.

**Main Flow:**

1. A participant completes a quiz.
2. The application records the participant's name and score.
3. The reviewer opens the results screen.
4. The application displays the participants and their scores.
5. The reviewer can compare participants' performances.

**Expected Outcome:** Quiz results are displayed in an organized summary.

---

## Minimal Viable Product (MVP)

The MVP will focus on implementing the core functionality needed to create, take, and review trivia quizzes. Each team
member will be responsible for implementing one primary use case.

| Team Member         | MVP Use Case          |
|---------------------|-----------------------|
| Edison Liao         | Create and Edit Quiz  |
| Eric Zhu            | Take Trivia Quiz      |
| Simon Kim           | Save and Load Quizzes |
| Tokhtar Yessergepov | View Quiz Results     |

Additional functionality, such as quiz previews, progress bars, and improved interface design, can be integrated as the
project develops.

*Note: These responsibilities are proposed and can be adjusted based on the team's preferences.*

---

## Entities

### Entity 1: Participant

**Description:** Represents a user who participates in trivia quizzes.

**Attributes:**

- `name`: The participant's name.
- `results`: A collection of the participant's completed quiz results.

**Responsibilities:**

- Store participant information.
- Associate participants with their quiz results.

### Entity 2: Question

**Description:** Represents an individual multiple-choice trivia question.

**Attributes:**

- `questionText`: The text of the question.
- `answerChoices`: A list of possible answers.
- `correctAnswerIndex`: The index of the correct answer.

**Responsibilities:**

- Store question content and possible answers.
- Determine whether a selected answer is correct.

### Entity 3: Quiz

**Description:** Represents a collection of trivia questions.

**Attributes:**

- `title`: The name of the quiz.
- `questions`: A collection of Question objects.
- `id`: A unique identifier for the quiz.

**Responsibilities:**

- Manage the collection of questions.
- Support adding, removing, and editing questions.
- Provide questions to active quiz sessions.

### Entity 4: QuizSession

**Description:** Represents a participant's attempt at completing a quiz.

**Attributes:**

- `participant`: The participant taking the quiz.
- `quiz`: The selected quiz.
- `currentQuestionIndex`: The current question number.
- `score`: The number of correctly answered questions.

**Responsibilities:**

- Track quiz progress.
- Process submitted answers.
- Calculate and update scores.
- Determine when the quiz is complete.

### Entity 5: QuizResult

**Description:** Represents the outcome of a completed quiz session.

**Attributes:**

- `participantName`: The participant's name.
- `quizId`: The identifier of the completed quiz.
- `score`: The number of correct answers.
- `totalQuestions`: The total number of questions.

**Responsibilities:**

- Store completed quiz results.
- Provide information for the results summary screen.

---

## API Usage

**API Name:** Open Trivia Database (OpenTDB)

**Documentation:** https://opentdb.com/api_config.php

**How it is used in the program:**

The Open Trivia Database API can be used to retrieve additional trivia questions from an external source.

The application can send HTTP requests to retrieve multiple-choice questions based on categories, difficulty levels, and
the desired number of questions.

For example:

`https://opentdb.com/api.php?amount=10&type=multiple`

This request retrieves 10 multiple-choice trivia questions.

The retrieved questions can be converted into Question entities and added to quizzes within the application.

The API will supplement the custom quiz creation functionality rather than replace it. Users will still be able to
create their own questions manually.

---

## Design Diagrams

### UML Class Diagram

The UML class diagram will illustrate the relationships between the main entities and the classes responsible for
application functionality.

The proposed design includes:

- **Participant:** Stores participant information.
- **Question:** Represents individual trivia questions.
- **Quiz:** Contains a collection of questions.
- **QuizSession:** Tracks an active quiz attempt.
- **QuizResult:** Stores completed quiz results.
- **QuizRepository:** Handles saving and loading quizzes.
- **QuizInteractor:** Coordinates the quiz-taking use case.
- **QuizController:** Receives user input from the interface.

The design will follow Clean Architecture principles by separating entities, use cases, interface adapters, and external
infrastructure.

### Sequence Diagrams

The following sequence diagrams will be created:

**1. Take Trivia Quiz**

- Participant selects an answer.
- View sends the selected answer to the controller.
- Controller invokes the quiz interactor.
- Interactor updates the quiz session.
- Presenter prepares the updated score and progress.
- View displays the next question.

**2. Create Quiz**

- Creator enters quiz information.
- View sends the information to the controller.
- Controller invokes the quiz creation interactor.
- Interactor creates or updates the Quiz entity.
- Repository saves the quiz.
- Presenter communicates the outcome to the view.

**3. View Quiz Results**

- Reviewer requests the results summary.
- Controller invokes the results interactor.
- Interactor retrieves stored results.
- Presenter formats participant names and scores.
- View displays the results summary.

---

## How to Run

*Proposed instructions for the Java application; update these once the implementation is finalized.*

1. Clone the project's GitHub repository.
2. Open the project in IntelliJ IDEA.
3. Ensure the required Java Development Kit (JDK) is installed and configured.
4. Install or configure any required dependencies.
5. Run the application's main class.
6. Use the graphical interface to create a quiz, take a quiz, or view results.

An internet connection may be required when retrieving questions from the Open Trivia Database API. Locally saved
quizzes should remain accessible without an internet connection.

---

## Notes

### Architecture

The project will follow Clean Architecture principles to separate business logic from user interface and data storage
implementations.

The application will be organized into the following layers:

- **Entities:** Core domain objects such as Quiz, Question, Participant, QuizSession, and QuizResult.
- **Use Cases:** Application-specific operations such as creating quizzes, submitting answers, and viewing results.
- **Interface Adapters:** Controllers, presenters, and view models that connect the interface to the use cases.
- **Frameworks and Drivers:** Graphical user interface, file storage, and external API integration.

### Data Persistence

Quiz data and participant results will be saved using local file storage, potentially in JSON format. This allows users
to retrieve their quizzes and results after restarting the application.

### Potential Extensions

If time permits, the team may implement additional features such as:

- Difficulty levels and question categories.
- A leaderboard ranking participants by score.
- Timed trivia questions.
- Randomized question order.
- Importing trivia questions from the Open Trivia Database API.

### Testing

Unit tests will be written for core entities and use cases, including answer validation, score calculation, question
management, and result storage.

The team will also test interactions between controllers, interactors, presenters, and data access components to verify
that the application behaves correctly.