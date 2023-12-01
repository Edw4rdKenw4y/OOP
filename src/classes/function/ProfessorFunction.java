package classes.function;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Flow.Processor;

import classes.exam.Exam;
import classes.question.Question;
import classes.repository.ExamRepository;
import classes.repository.QuestionRepository;
import classes.subject.Subject;
import classes.util.Constant;
import classes.util.Date;

public class ProfessorFunction {
    
    public static void ShowQuesBank()
    {
        QuestionRepository questionRepository = new QuestionRepository(Constant.dataPath.QuestionBanks_Dir);
        System.out.println("Question List:");
                for (Question ques : questionRepository.getQuesbank().getQuesList()) {
            System.out.println(ques);
            }
    }

    public static void addQuestion(Scanner sc)
    {
        QuestionRepository questionRepository = new QuestionRepository(Constant.dataPath.QuestionBanks_Dir);
        System.out.println("Choose chapter :");
        int chapter = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter content: ");
        String content = sc.nextLine();
        System.out.println("Choose difficulty [1] - [4]");
        int difficulty = sc.nextInt();
        sc.nextLine(); 

        String[] answer = new String[4];
        for (int i = 0; i < answer.length; i++) {
            System.out.println("Enter answer " + (i + 1) + ": ");
            answer[i] = sc.nextLine();
        }
        
        System.out.println("Enter correct answer [1 - 4]: ");
        int correctAnswer = sc.nextInt();
        sc.nextLine(); 

        Question newQuestion = new Question(chapter, difficulty, content, answer, correctAnswer);
        questionRepository.addQuestion(newQuestion);
        System.out.println("Question has been added.");
    }

    public static void removeQuestion(Scanner sc) {
        QuestionRepository questionRepository = new QuestionRepository(Constant.dataPath.QuestionBanks_Dir);
    
        System.out.println("Choose delete method");
        System.out.println("[1] Delete by content");
        System.out.println("[2] Delete by chapter");
        System.out.println("[3] Delete by difficulty");
        int choice = sc.nextInt();
        sc.nextLine();
    
        switch (choice) {
            case 1:
                removeQuestionByContent(sc, questionRepository);
                break;
            case 2:
                removeQuestionByChapter(sc, questionRepository);
                break;
            case 3:
                removeQuestionByDifficulty(sc, questionRepository);
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }
    }
    
    private static void removeQuestionByContent(Scanner sc, QuestionRepository questionRepository) {
        System.out.println("Enter content of question: ");
        String questionContent = sc.nextLine();
    
        // Find question indices based on content
        ArrayList<Integer> indices = questionRepository.indexSearchQuestionByContent(questionContent);
    
        if (indices.isEmpty()) {
            System.out.println("Not found question with " + questionContent);
            return;
        }
    
        System.out.println("Founded questions:");
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            System.out.println("[" + (i + 1) + "] " + questionRepository.getQuesbank().getQuesList().get(index));
        }
    
        System.out.println("Choose question to remove:");
        int choice = sc.nextInt();
        sc.nextLine();
    
        if (choice >= 1 && choice <= indices.size()) {
            int indexToRemove = indices.get(choice - 1);
            questionRepository.removeQuestion(indexToRemove);
            System.out.println("Question deleted.");
        } else {
            System.out.println("Wrong input");
        }
    }
    
    private static void removeQuestionByChapter(Scanner sc, QuestionRepository questionRepository) {
        System.out.println("Enter chapter of question");
        int chapter = sc.nextInt();
        sc.nextLine();
    
        ArrayList<Integer> indices = questionRepository.indexSearchQuestionByChapter(chapter);
    
        if (indices.isEmpty()) {
            System.out.println("Question not found in chapter " + chapter);
            return;
        }
        System.out.println("Founded questions");
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            System.out.println("[" + (i + 1) + "] " + questionRepository.getQuesbank().getQuesList().get(index));
        }
    
        System.out.println("Choose question to remove");
        int choice = sc.nextInt();
        sc.nextLine();
    
        if (choice >= 1 && choice <= indices.size()) {
            int indexToRemove = indices.get(choice - 1);
            questionRepository.removeQuestion(indexToRemove);
            System.out.println("Question deleted.");
        } else {
            System.out.println("Wrong input");
        }
        // Implement removal logic based on chapter indices if needed
    }
    
    private static void removeQuestionByDifficulty(Scanner sc, QuestionRepository questionRepository) {
        System.out.println("Nhap do kho cau hoi can xoa: ");
        int difficulty = sc.nextInt();
        sc.nextLine();
    
        ArrayList<Integer> indices = questionRepository.indexSearchQuestionByDiffi(difficulty);
    
        if (indices.isEmpty()) {
            System.out.println("Question not found with difficulty " + difficulty);
            return;
        }
        System.out.println("Founded questions:");
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            System.out.println("[" + (i + 1) + "] " + questionRepository.getQuesbank().getQuesList().get(index));
        }
    
        System.out.println("Choose question to remove");
        int choice = sc.nextInt();
        sc.nextLine();
    
        if (choice >= 1 && choice <= indices.size()) {
            int indexToRemove = indices.get(choice - 1);
            questionRepository.removeQuestion(indexToRemove);
            System.out.println("Question deleted.");
        } else {
            System.out.println("Wrong input");
        }
    
        // Implement removal logic based on difficulty indices if needed
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // ProfessorFunction.addQuestion(sc);
        ProfessorFunction.ShowQuesBank();
        ProfessorFunction.removeQuestion(sc);
    }
}
