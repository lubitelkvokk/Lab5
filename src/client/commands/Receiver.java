package client.commands;

//Invoker
public class Receiver {
    /**
     * Команды, вводимые пользователем
     */
    private Command helpCommand;
    private Command addCommand;
    private Command infoCommand;

    /**
     * Конструктор, в который передаются команды, доступные пользователю
     *
     * @param help
     */
    public Receiver(HelpCommand help, AddCommand add, InfoCommand info) {
        this.helpCommand = help;
        this.addCommand = add;
        this.infoCommand = info;
    }


    public void searchCommand(String command){
        switch (command.trim()) {
            case ("help"):
                help();
                break;
            case ("info"):
                info();
                break;
            case ("add"):
                add();
                break;
            case ("exit"):
                exit();
                break;
            default:
                System.out.println("Команда введена некорректно");
                break;
        }
    }
    public void help() {
        helpCommand.execute();
    }

    public void add() {
        addCommand.execute();
    }

    public void info() {
        infoCommand.execute();
    }

    public void exit(){
        System.out.println("-------END--------");
    }
}
