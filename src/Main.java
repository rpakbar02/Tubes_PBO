import crud.*;

public class Main {
    public static void main(String[] args) {
        Role admin = new Admin();
        Role manager = new Manager();
        Role member = new Member();

        User admin1 = new User("admin", "admin@example.com", "adminpassword1", admin);
        User user1 = null;
        User user2 = null;
        User user3;
        if (admin1.getRole() instanceof Admin adm) {
            user1 = adm.createUser();
            user2 = adm.createUser();
            user3 = adm.createUser();

            user3.viewProfile();
            adm.deleteUser(user3);
        }
        
        user1.createProject();
        user1.viewProfile();
        user1.removeProject(user1.getProjects().get(0));
        user1.viewProfile();
        user1.createProject();
        user1.viewProject(user1.getProjects().get(0));

        if(user1.getRole() instanceof Manager man) {
            System.out.println("manageproj1");
            man.manageProject(user1.getProjects());
            System.out.println("manageprojnomor2");
            man.manageProject(user1.getProjects(), user2);
            System.out.println("manageprojnomor3");
            man.manageProject(user1.getProjects());
            System.out.println("manageprojnomor4");
            man.manageProject(user1.getProjects());
            System.out.println("manageprojnomor5");
            man.manageProject(user1.getProjects());
        }

        if(user2.getRole() instanceof Member mem) {
            mem.writeReport(user2.getProjects().get(0), user1.getProjects().get(0).getTaskList().get(0), user2);
        }

        user1.getProjects().get(0).getTaskList().get(0).viewReport();
        user1.getProjects().get(0).getTaskList().get(0).viewSubtask();
        user1.getProjects().get(0).getTaskList().get(0).viewWorkload();
    }
}
