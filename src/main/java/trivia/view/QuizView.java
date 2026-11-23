package trivia.view;

import trivia.entity.Quiz;
import trivia.interface_adapter.take_quiz.QuizController;
import trivia.interface_adapter.take_quiz.QuizViewModel;
import trivia.use_case.take_quiz.QuizOutputData;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/** Swing view: renders state; never checks answers or modifies scores itself. */
public final class QuizView extends JFrame implements PropertyChangeListener {
    private static final String START = "start";
    private static final String PLAY = "play";
    private static final String DONE = "done";

    private final QuizController controller;
    private final CardLayout cards = new CardLayout();
    private final JPanel cardPanel = new JPanel(cards);
    private final JTextField nameField = new JTextField(25);
    private final JComboBox<QuizChoice> quizChoices = new JComboBox<>();
    private final JLabel quizTitle = new JLabel();
    private final JLabel scoreLabel = new JLabel();
    private final JLabel questionPosition = new JLabel();
    private final JTextArea questionText = new JTextArea();
    private final JPanel answersPanel = new JPanel();
    private final JProgressBar progress = new JProgressBar();
    private final JLabel feedback = new JLabel(" ");
    private final JLabel finalScore = new JLabel("", SwingConstants.CENTER);
    private final JLabel finalMessage = new JLabel("", SwingConstants.CENTER);

    private ButtonGroup answerGroup = new ButtonGroup();
    private List<JRadioButton> answerButtons = new ArrayList<>();

    public QuizView(QuizController controller, QuizViewModel viewModel, List<Quiz> quizzes) {
        super("Trivia Quiz Program");
        this.controller = controller;
        viewModel.addPropertyChangeListener(this);
        for (Quiz quiz : quizzes) {
            quizChoices.addItem(new QuizChoice(quiz.getId(), quiz.getTitle()));
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(560, 440));
        setSize(700, 530);
        setLocationRelativeTo(null);
        cardPanel.add(createStartPanel(), START);
        cardPanel.add(createPlayPanel(), PLAY);
        cardPanel.add(createDonePanel(), DONE);
        add(cardPanel);
        cards.show(cardPanel, START);
    }

    private JPanel createStartPanel() {
        JPanel content = paddedPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JLabel heading = new JLabel("Take Trivia Quiz");
        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 25f));
        heading.setAlignmentX(CENTER_ALIGNMENT);
        content.add(heading);
        content.add(Box.createVerticalStrut(30));
        content.add(new JLabel("Participant name:"));
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        content.add(nameField);
        content.add(Box.createVerticalStrut(15));
        content.add(new JLabel("Choose a quiz:"));
        quizChoices.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        content.add(quizChoices);
        content.add(Box.createVerticalStrut(25));
        JButton start = new JButton("Start Quiz");
        start.setAlignmentX(CENTER_ALIGNMENT);
        start.addActionListener(event -> {
            QuizChoice selected = (QuizChoice) quizChoices.getSelectedItem();
            controller.startQuiz(nameField.getText(), selected == null ? null : selected.getId());
        });
        content.add(start);
        return content;
    }

    private JPanel createPlayPanel() {
        JPanel content = paddedPanel();
        content.setLayout(new BorderLayout(12, 15));
        JPanel header = new JPanel(new GridLayout(4, 1, 0, 6));
        quizTitle.setFont(quizTitle.getFont().deriveFont(Font.BOLD, 20f));
        header.add(quizTitle);
        header.add(scoreLabel);
        header.add(questionPosition);
        progress.setStringPainted(true);
        header.add(progress);
        content.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(5, 15));
        questionText.setEditable(false);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        questionText.setOpaque(false);
        questionText.setFont(questionText.getFont().deriveFont(Font.BOLD, 17f));
        questionText.setRows(3);
        center.add(questionText, BorderLayout.NORTH);
        answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.Y_AXIS));
        center.add(new JScrollPane(answersPanel), BorderLayout.CENTER);
        content.add(center, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout(0, 10));
        footer.add(feedback, BorderLayout.NORTH);
        JButton submit = new JButton("Submit Answer");
        submit.addActionListener(event -> {
            Integer selection = null;
            for (int i = 0; i < answerButtons.size(); i++) {
                if (answerButtons.get(i).isSelected()) {
                    selection = i;
                    break;
                }
            }
            controller.submitAnswer(selection);
        });
        footer.add(submit, BorderLayout.SOUTH);
        content.add(footer, BorderLayout.SOUTH);
        return content;
    }

    private JPanel createDonePanel() {
        JPanel content = paddedPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createVerticalGlue());
        JLabel heading = new JLabel("Quiz Complete!", SwingConstants.CENTER);
        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 26f));
        heading.setAlignmentX(CENTER_ALIGNMENT);
        finalScore.setFont(finalScore.getFont().deriveFont(Font.BOLD, 22f));
        finalScore.setAlignmentX(CENTER_ALIGNMENT);
        finalMessage.setAlignmentX(CENTER_ALIGNMENT);
        content.add(heading);
        content.add(Box.createVerticalStrut(25));
        content.add(finalScore);
        content.add(Box.createVerticalStrut(12));
        content.add(finalMessage);
        content.add(Box.createVerticalStrut(35));
        JButton restart = new JButton("Take Another Quiz");
        restart.setAlignmentX(CENTER_ALIGNMENT);
        restart.addActionListener(event -> cards.show(cardPanel, START));
        content.add(restart);
        content.add(Box.createVerticalGlue());
        return content;
    }

    private static JPanel paddedPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(28, 35, 28, 35));
        return panel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("error".equals(event.getPropertyName())) {
            JOptionPane.showMessageDialog(this, event.getNewValue(), "Quiz", JOptionPane.WARNING_MESSAGE);
        } else if ("state".equals(event.getPropertyName())) {
            display((QuizOutputData) event.getNewValue());
        }
    }

    private void display(QuizOutputData state) {
        if (state.isFinished()) {
            finalScore.setText(state.getParticipantName() + ": " + state.getScore() + " / " + state.getTotalQuestions());
            finalMessage.setText(state.getStatusMessage());
            cards.show(cardPanel, DONE);
            return;
        }

        quizTitle.setText(state.getQuizTitle());
        scoreLabel.setText("Score: " + state.getScore());
        questionPosition.setText("Question " + (state.getAnsweredCount() + 1) + " of " + state.getTotalQuestions());
        progress.setMaximum(state.getTotalQuestions());
        progress.setValue(state.getAnsweredCount());
        progress.setString(state.getAnsweredCount() + " / " + state.getTotalQuestions() + " answered");
        feedback.setText(state.getStatusMessage());
        questionText.setText(state.getQuestionText());
        questionText.setCaretPosition(0);

        answersPanel.removeAll();
        answerButtons = new ArrayList<>();
        answerGroup = new ButtonGroup();
        for (String choice : state.getAnswerChoices()) {
            JRadioButton button = new JRadioButton(choice);
            button.setFont(button.getFont().deriveFont(15f));
            button.setAlignmentX(LEFT_ALIGNMENT);
            answerGroup.add(button);
            answerButtons.add(button);
            answersPanel.add(button);
            answersPanel.add(Box.createVerticalStrut(10));
        }
        answersPanel.revalidate();
        answersPanel.repaint();
        cards.show(cardPanel, PLAY);
    }

    private static final class QuizChoice {
        private final String id;
        private final String title;

        private QuizChoice(String id, String title) {
            this.id = id;
            this.title = title;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
