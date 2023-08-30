package Duke;

import Duke.Commands.Command;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    public Storage dataBase;
    private Ui ui;
    private String filePath = "./tasklist.txt";

    /**
     * Chatbot constructor
     * @throws IOException if there is an error loading the saved file
     */
    public Duke() throws IOException {
        ui = new Ui();
        this.dataBase = new Storage(filePath);
        this.taskList = dataBase.load();
    }

    /**
     * The factory method to run the chatbot after it has been instantiated. It is responsible for scanning inputs and
     * passing the inputs to the parsers to parse and instantiate the respective commands
     */
    public void run() throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ui.printHello();

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        do {
            userInput = sc.nextLine();
            Command c = Parser.parse(userInput, this.taskList, this.dataBase, ui);
            c.execute();
        } while (!userInput.toLowerCase().equals("bye"));
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke bot = new Duke();
        bot.run();
    }
}