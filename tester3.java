package confesstime;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class tester3 {

    static Data data = new Data();
    static ConfessionPost post = new ConfessionPost();
    static GenericStack<DoublyLinkedList> stackList = new GenericStack<>();
    static GenericStack<String> stackCurrent = new GenericStack<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String cont = "Y";

        while (cont.equalsIgnoreCase("Y")) {
            submitList();
            System.out.println(">> Do you want to continue:");
            System.out.println(">> \"Y\" - Yes");
            System.out.println(">> \"N\" - No");
            cont = in.nextLine();
        }

        System.out.println("===============================================================");
        System.out.println(">> All Post");
        showAllReplies(data.tree);
        System.out.println("===============================================================");
    }

    public static void submitList() {
        Scanner in = new Scanner(System.in);
        System.out.println("===============================================================");
        System.out.println(">> Options:");
        System.out.println(">> \"T\" - Type new confession post");
        System.out.println(">> \"R\" - Reply confession post");
        String input = in.nextLine();
        option1(input);
    }

    public static void option1(String input) {
        Scanner in = new Scanner(System.in);

        if (input.equalsIgnoreCase("T")) {
            enterConfession("UM00");
        } else if (input.equalsIgnoreCase("R")) {
            System.out.println("===============================================================");
            System.out.println(">> Please enter the confession post ID you want to reply.");
            System.out.println(">> Format: UM00 or UM010");
            System.out.println("---------------------------------------------------------------");
            System.out.print("Reply confession post ID: ");
            String input1 = in.nextLine();
            System.out.println("---------------------------------------------------------------");

            if (searchPublishedPost(data.tree, input1) == true) {
                enterConfession(input1);
            }

        } else {
            submitList();
        }
    }

    public static boolean searchPublishedPost(ReplyNode<ConfessionPost> tree, String input) { ///ubah loop
        boolean postFound = false;
        if (input.equals("-1") || input.equals("") || input.isBlank()) {
            System.out.println(">> Confession post ID doesn't exists!");
            System.out.println("===============================================================");
        } else {
            for (ReplyNode<ConfessionPost> node : tree) {
                if (node.data.getID().contains(input)) {
                    System.out.println(">> Confession post ID exists!");
                    postFound = true;
                }
            }
            if (!postFound) {
                System.out.println(">> Confession post ID doesn't exists!");
                System.out.println("===============================================================");
            }
        }
        return postFound;
    }

    public static String enterConfession(String input) {
        Scanner in = new Scanner(System.in);
        ConfessionPost newPost = new ConfessionPost();
        Date submitTime = new Date();
        System.out.println("===============================================================");
        System.out.println(">> Please enter your confession content.");
        System.out.println(">> Insert \"-1\" to submit your confession or exit.");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Confession content:");
        String str, content = "";
        str = in.nextLine();
        if (str.equals("-1") || str.equals("") || str.isBlank()) {
            System.out.println("===============================================================");
            System.out.println(">> No post submitted");
            System.out.println("===============================================================");
            return "";
        }

        while (!str.equals("-1")) {
            content += str;
            str = in.nextLine();
        }

        String newID = newPost.addPost(content);
        System.out.println("===============================================================");
        System.out.println(">> Submitted at " + submitTime);
        System.out.println(">> Confession post ID " + newID);
        System.out.println(">> Your confession will be published soon.");
        System.out.println("===============================================================");
        boolean proceed = spamChecking(content);
        if (proceed == true) {
            waitingList(content, newID, input);
        }
        return newID;
    }

    public static void postDetail(ReplyNode<ConfessionPost> tree, String id) {
        System.out.println(">> Published at " + data.searchData(id).data.getTime());
        System.out.println(">> Confession post ID " + data.searchData(id).data.getID());
        System.out.print("===============================================================\n");
    }

    public static void showAllReplies(ReplyNode<ConfessionPost> tree) {
        for (ReplyNode<ConfessionPost> node : tree) {
            System.out.println(node.data);
            System.out.println(node.getLevel());
        }
    }

    public static int count(String str) {
        int count = 1;
        for (int i = 0; i < str.length() - 1; i++) {
            if ((str.charAt(i) == ' ') && (str.charAt(i + 1) != ' ')) {
                count++;
            }
        }
        return count;
    }

    public static boolean spamChecking(String input) {
        ConfessionPost newPost = new ConfessionPost();
        Boolean postFound = true;
        for (ReplyNode<ConfessionPost> node : data.tree) {
            if (node.data.getContent().equalsIgnoreCase(input)) {
                System.out.println(">> Spamming Alert");
                System.out.println(">> Same with post: " + node.data.getID());
                System.out.println(">> Post is Removed");
                System.out.println("===============================================================");
                newPost.setPost(null, null, null);
                postFound = false;
                break;
            }
        }
        return postFound;
    }

    public static void publishPost(String content, String postID, String input, int sleep) {
        ConfessionPost newPost = new ConfessionPost();
        try {
            System.out.println(">> Waiting to Post: " + new Date());
            //TimeUnit.MINUTES.sleep(sleep); // for submission
            TimeUnit.SECONDS.sleep(sleep); //for testing
            newPost.setPost(content, postID, new Date());
            data.insertData(input, newPost);
            postDetail(data.tree, postID);
        } catch (InterruptedException ex) {
        }

    }

    public static void waitingList(String content, String ID, String input) {

        GenericQueue<String> queueInput = new GenericQueue<>();
        GenericQueue<String> queueId = new GenericQueue<>();
        queueInput.enqueue(content);
        queueId.enqueue(ID);

        while (!queueInput.isEmpty()) {
            int length = count(queueInput.peek());
            if (length <= 10) {
                if (length <= 5) {
                    publishPost(queueInput.dequeue(), queueId.dequeue(), input, 15);
                } else {
                    publishPost(queueInput.dequeue(), queueId.dequeue(), input, 10);
                }
            } else {
                publishPost(queueInput.dequeue(), queueId.dequeue(), input, 5);
            }
        }

    }

}
