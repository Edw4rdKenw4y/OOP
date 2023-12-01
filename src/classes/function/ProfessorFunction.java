package classes.function;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Flow.Processor;

import classes.exam.*;
import classes.question.Question;
import classes.question.QuestionCountDetail;
import classes.question.QuestionSet;
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

    private static void removeQuestion(Scanner sc) {
        QuestionRepository questionRepository = new QuestionRepository(Constant.dataPath.QuestionBanks_Dir);

        System.out.println("Choose delete method");
        System.out.println("[1] Delete by content");
        System.out.println("[2] Delete by chapter");
        System.out.println("[3] Delete by difficulty");

        int choice = sc.nextInt();
        sc.nextLine();

        ArrayList<Integer> indices = new ArrayList<>();

        switch (choice) {
            case 1:
                System.out.println("Enter content of question:");
                indices = questionRepository.indexSearchQuestionByContent(sc.nextLine());
                break;
            case 2:
                System.out.println("Enter chapter of question:");
                indices = questionRepository.indexSearchQuestionByChapter(sc.nextInt());
                break;
            case 3:
                System.out.println("Enter difficulty of question:");
                indices = questionRepository.indexSearchQuestionByDiffi(sc.nextInt());
                break;
            default:
                System.out.println("Incorrect input");
                return;
        }

        if (indices.isEmpty()) {
            System.out.println("Question not found.");
            return;
        }

        System.out.println("Founded questions:");
        indices.forEach(i -> System.out.println("[" + (i + 1) + "] " + questionRepository.getQuesbank().getQuesList().get(i)));

        System.out.println("Choose question to remove:");
        int choiceToRemove = sc.nextInt();
        sc.nextLine();

        if (choiceToRemove >= 1 && choiceToRemove <= indices.size()) {
            int indexToRemove = indices.get(choiceToRemove - 1);
            questionRepository.removeQuestion(indexToRemove);
            System.out.println("Question deleted.");
        } else {
            System.out.println("Wrong input");
        }
    }

    private static void modifyQuestion(Scanner sc) {
        QuestionRepository questionRepository = new QuestionRepository(Constant.dataPath.QuestionBanks_Dir);

        System.out.println("Choose modification method:");
        System.out.println("[1] Modify by content");
        System.out.println("[2] Modify by chapter");
        System.out.println("[3] Modify by difficulty");

        int choice = sc.nextInt();
        sc.nextLine();

        ArrayList<Integer> indices = new ArrayList<>();

        switch (choice) {
            case 1:
                System.out.println("Enter content of question to modify:");
                indices = questionRepository.indexSearchQuestionByContent(sc.nextLine());
                break;
            case 2:
                System.out.println("Enter chapter of question to modify:");
                indices = questionRepository.indexSearchQuestionByChapter(sc.nextInt());
                break;
            case 3:
                System.out.println("Enter difficulty of question to modify:");
                indices = questionRepository.indexSearchQuestionByDiffi(sc.nextInt());
                break;
            default:
                System.out.println("Incorrect input");
                return;
        }

        if (indices.isEmpty()) {
            System.out.println("Question not found.");
            return;
        }

        System.out.println("Founded questions:");
        indices.forEach(i -> System.out.println("[" + (i + 1) + "] " + questionRepository.getQuesbank().getQuesList().get(i)));

        System.out.println("Choose question to modify:");
        int choiceToModify = sc.nextInt();
        sc.nextLine();

        if (choiceToModify >= 1 && choiceToModify <= indices.size()) {
            int indexToModify = indices.get(choiceToModify - 1);
            modifyQuestionAtIndex(sc, questionRepository, indexToModify);
        } else {
            System.out.println("Wrong input");
        }
    }

    private static void modifyQuestionAtIndex(Scanner sc, QuestionRepository questionRepository, int indexToModify) {
        Question questionToModify = questionRepository.getQuesbank().getQuesList().get(indexToModify);

        System.out.println("Enter new content:");
        String newContent = sc.nextLine();

        System.out.println("Enter new chapter:");
        int newChapter = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter new difficulty:");
        int newDifficulty = sc.nextInt();
        sc.nextLine();

        String[] newAnswer = new String[4];
        for (int i = 0; i < newAnswer.length; i++) {
            System.out.println("Enter new answer " + (i + 1) + ":");
            newAnswer[i] = sc.nextLine();
        }

        System.out.println("Enter new correct answer [1 - 4]:");
        int newCorrectAnswer = sc.nextInt();
        sc.nextLine();

        // Modify the question
        Question modifiedQuestion = new Question(newChapter, newDifficulty, newContent, newAnswer, newCorrectAnswer);
        questionRepository.modifyQuestion(indexToModify, modifiedQuestion);

        System.out.println("Question modified.");
    }

    public static void addExam(Scanner sc, Subject subject) {
        
        System.out.println("Nhap ten class:");
        String clazz = sc.nextLine();  
        Date date = new Date();

        System.out.println("nhap ngay thi:");
        String newDate  = sc.nextLine();
        System.out.println("nhap thang:");
        String newMonth  = sc.nextLine();
        System.out.println("nhap nam:");
        String newYear  = sc.nextLine();

        date.setDay(newDate);
        date.setMonth(newMonth);
        date.setYear(newYear);


        ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);

        System.out.println("nhap exam id:");
        String examId = sc.nextLine();
        System.out.println("nhap exam name:");
        String examName = sc.nextLine();
        System.out.println("nhap exam note:");
        String examNote = sc.nextLine();
        System.out.println("nhap exam time:");
        int examTime = sc.nextInt();
        sc.nextLine();

        System.out.println("nhap so luong cau hoi");
        int quesCount = sc.nextInt();
        sc.nextLine();

        ArrayList<QuestionCountDetail> questionCountDetails;

        ArrayList<Integer> diffs = new ArrayList<Integer>();

        for (QuestionCountDetail questionCD : questionCountDetails) {
            System.out.println("chon chuong:");
            int chapter = sc.nextInt();
            sc.nextLine();
            System.out.println("nhap so luong cau hoi de");
            int ezQues = sc.nextInt();
            sc.nextLine();
            diffs.add(ezQues);

            System.out.println("nhap so luong cau hoi tb");
            int meQues = sc.nextInt();
            sc.nextLine();
            diffs.add(meQues);

            System.out.println("nhap so luong cau hoi kho");
            int haQues = sc.nextInt();
            sc.nextLine();
            diffs.add(haQues);

            questionCD.setChapter(chapter);
            questionCD.setDifficultyCountDetail(diffs);
            questionCountDetails.add(questionCD);
            diffs.clear();
        }

        QuestionSet questionSet = new QuestionSet(subject, quesCount, questionCountDetails);

        QuestionRepository questionRepository = new QuestionRepository(Constant.dataPath.QuestionBanks_Dir);

        questionRepository.getQuesbank().getQuesList();

        Exam exam = new Exam(examId, examName, subject, examNote, date, examTime, questionSet);
        exam.setDate(date);

        System.out.println("Enter exam file name: ");
        String examFileName = sc.nextLine();

        if (examRepository.addExam(exam, examFileName)) {
            System.out.println("Exam added successfully.");
        } else {
            System.out.println("Failed to add exam.");
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProfessorFunction.addQuestion(sc);
        ProfessorFunction.ShowQuesBank();
        ProfessorFunction.removeQuestion(sc);
        ProfessorFunction.modifyQuestion(sc);
    }
}
