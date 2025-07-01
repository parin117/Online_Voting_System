import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets; // to access data written in files;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

class election{
    election head;
    String name;
    String date;
    election next;
    election(){
        this.name = null;
        this.date = null;
        this.next = null;
    }
    election(String name, String date){
        this.name = name;
        this.date = date;
        this.next = null;
    }
}

class candidate{
    candidate head;
    String name;
    int age;
    String voterId;
    String nationality;
    String Address;
    String partyname;
    candidate next;
    candidate(){
        this.name = null;
        this.age = 0;
        this.voterId = null;
        this.nationality = null;
        this.Address = null;
        this.partyname = null;
        this.next = null;
    }
    candidate(String name, int age, String voterId, String nationality, String Address, String partyname){
        this.name = name;
        this.age = age;
        this.voterId = voterId;
        this.nationality = nationality;
        this.Address = Address;
        this.partyname = partyname;
        this.next = null;
    }
}

class voter{
    voter head;
    String vname;
    String age;
    String voterId;
    voter next;
    voter() {
        this.vname = null;
        this.age = null;
        this.voterId = null;
        this.next = null;
    }
    voter(String vname, String age, String voterId) {
        this.vname = vname;
        this.age = age;
        this.voterId = voterId;
        this.next = null;
    }
}

class administrator{
    // to create node in election linked list;
    election addElection(election e, election new_node) {
        if(e.head==null) {
            e.head=new_node;
        }else{
            election ptr=e.head;
            while(ptr.next!=null){
                ptr=ptr.next;
            }
            ptr.next = new_node;
        }
        return e;
    }
    // to create node in candidate linked list;
    candidate addCandidate(candidate c, candidate new_node){
        if(c.head==null) {
            c.head=new_node;
        }else{
            candidate ptr=c.head;
            while(ptr.next!=null) {
                ptr=ptr.next;
            }
            ptr.next=new_node;
        }
        return c;
    }
    // to create node in voter linked list;
    voter addVoter(voter v, voter new_node) {
        if(v.head==null) {
            v.head=new_node;
        }else{
            voter ptr=v.head;
            while(ptr.next!=null){
                ptr=ptr.next;
            }
            ptr.next=new_node;
        }
        return v;
    }
    // method to add election;
    void eadd(election e){
        Scanner sc=new Scanner(System.in);
        String name;
        String date;
        System.out.println();
        System.out.print("\t\t\t\t\tEnter Election Name : ---> ");
        name = sc.nextLine(); 
        System.out.println();
        System.out.print("\t\t\t\t\tEnter Election Date(DD/MM/YYYY) : ---> ");
        date = sc.nextLine(); 
        System.out.println();
        election new_node=new election(name,date);
        e=addElection(e,new_node);
        writeToEleFile(e,new_node);
    }
    // method to write node data of election in a file of election;
    void writeToEleFile(election e, election new_node) {
        File file=new File("election.csv");
        try{
            FileWriter fw = new FileWriter("election.csv", true); // append true to avoid overwriting in file;
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.printf("%s,%s\n", new_node.name, new_node.date);
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException f){
            f.printStackTrace(); // trace error on which line;
        }finally{
        }
    }
    // method to add candidate;
    void cadd(candidate c){
        Scanner sc = new Scanner(System.in);
        String cname;
        String cvoterid;
        int cage;
        String cnationality;
        String cparthy;
        String caddress;
        String nation = "INDIAN";
        System.out.println();
        System.out.print("\t\t\t\t\tEnter Candidate Name : ---> ");
        cname = sc.next();
        if (!cname.matches("^[a-zA-Z]*$")) {
            System.out.println("Input should contain only alphabetic characters.");
            return;
        }
        System.out.println();
        System.out.print("\t\t\t\t\tEnter Candidate Age : ---> ");
        cage = sc.nextInt();
        if (cage < 19){
            System.out.println("\t\t\t\t\tCandidate did not elegible for election due to age criteria..!");
            return;
        }
        System.out.println();
        System.out.print("\t\t\t\t\tEnter Candidate Voter-Id (12 DIGITS) : ---> ");
        cvoterid = sc.next();
        System.out.println();
        while(cvoterid.length()!=12){
            System.out.println("\t\t\t\t\tInvalid Voter Id !! Please Enter Again :)");
            System.out.println();
            System.out.print("\t\t\t\t\tEnter Candidate Voter-Id : ---> ");
            cvoterid = sc.next();
        }
        System.out.print("\t\t\t\t\tEnter Candidate nationality :(If india than type INDIAN) ---> ");
        cnationality = sc.next();
        System.out.println();
        if (nation.compareTo(cnationality)!=0) {
            System.out.println("\t\t\t\t\tYou are not inhabitant of india..!");
            System.out.println("\t\t\t\t\tYou can't parcipate in election :(");
            System.out.println();
            return;
        }
        System.out.print("\t\t\t\t\tEnter Candidate Address : ---> ");
        caddress = sc.next();
        System.out.println();
        System.out.print("\t\t\t\t\tEnter Candidate party name : ---> ");
        cparthy = sc.next();
        System.out.println();
        candidate new_node = new candidate(cname, cage, cvoterid, cnationality, caddress, cparthy);
        c=addCandidate(c,new_node);
        writeToCanFile(c,new_node);
        writeToVotfile1(c,new_node);
    }
    // method to write node data of candidates in a file of candidate;
    void writeToCanFile(candidate c, candidate new_node) {
        File file=new File("candidate.csv");
        try{
            FileWriter fw = new FileWriter("candidate.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.printf("%s,%d,%s,%s,%s,%s\n", new_node.name, new_node.age, new_node.voterId, new_node.nationality, new_node.Address, new_node.partyname);
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException f){
            f.printStackTrace();
        }finally{
        }
    }
    // method to write node data of candidates in a file of voters;
    void writeToVotfile1(candidate c, candidate new_node) {
        File file=new File("voter.csv");
        try{
            FileWriter fw=new FileWriter("voter.csv", true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            pw.printf("%s,%s,%s\n", new_node.name, new_node.age, new_node.voterId);
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException f){
            f.printStackTrace();
        }finally{
        }
    }
    // method to write node data of voters in a file of voters;
    void writeToVotfile(voter v, voter new_node) {
        File file=new File("voter.csv");
        try{
            FileWriter fw = new FileWriter("voter.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.printf("%s,%s,%s\n", new_node.vname, new_node.age, new_node.voterId);
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException f){
            f.printStackTrace();
        }finally{
        }
    }
    // method to add voters;
    void addvoter(voter v) {
        File file=new File("voter.csv"); 
        Path pathToFile=Paths.get("voter.csv");
        Scanner sc = new Scanner(System.in);
        String name;
        String age;
        String voterid;
        try{
            BufferedReader br=Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII);
            String line = br.readLine();
            System.out.print("\t\t\t\t\tEnter Voter Name : ---> ");
            name = sc.nextLine();
            System.out.println();
            System.out.print("\t\t\t\t\tEnter Voter's Age : ---> ");
            age = sc.nextLine();
            System.out.println();
            System.out.print("\t\t\t\t\tEnter Voter-Id (12 DIGITS) : ---> ");
            voterid = sc.nextLine();
            System.out.println();
            while (voterid.length()!=12) {
                System.out.println("\t\t\t\t\tInvalid Voter Id !! Please Enter Again :)");
                System.out.println();
                System.out.print("\t\t\t\t\tEnter Voter-Id : ---> ");
                voterid = sc.next();
            }
            while(line!=null){
                String[] voters=line.split(",");
                String voter_id=voters[2];
                if(voterid.compareTo(voter_id)==0)
                {
                    System.out.println("\t\t\t\t\tVoter with this voter id already present there in voter's list..!");
                    return;
                }
                line = br.readLine();  
            }
            voter new_node=new voter(name, age, voterid);
            writeToVotfile(v, new_node);
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    // method to display list of voters;
    void votdisplay(voter v){
        Path pathToFile=Paths.get("voter.csv");
        try{
            BufferedReader br=Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII);
            String line=br.readLine();
            // line = br.readLine();
            while(line!=null) {
                String[] voters = line.split(",");
                String name=voters[0];
                String age=voters[1];
                String voter_id=voters[2];
                System.out.println();
                System.out.println("\n==================================================================================================================================\n");
                System.out.println("\t\t\t\t\tVoter's Name : ---> " + name);
                System.out.println();
                System.out.println("\t\t\t\t\tVoter's birth-date : ---> "+age);
                System.out.println();
                System.out.println("\t\t\t\t\tVoter's Voter-id : ---> "+voter_id);
                line = br.readLine();
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println("\n==================================================================================================================================\n");
        System.out.println();
        // return v;
    }
    // method to display list of candidates;
    void candisplay(candidate c) {
        Path pathToFile=Paths.get("candidate.csv");
        try{
            BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII);
            String line = br.readLine();
            // line = br.readLine();
            while(line!=null) {
                String[] can=line.split(",");
                String name=can[0];
                String age=can[1];
                String voter_id=can[2];
                String nationality=can[3];
                String Address=can[4];
                String partyname=can[5];
                System.out.println();
                System.out.println("\n==================================================================================================================================\n");
                System.out.println("\t\t\t\t\tCandidate's Name : ---> "+name);
                System.out.println();
                System.out.println("\t\t\t\t\tCandidate's age : ---> " +age);
                System.out.println();
                System.out.println("\t\t\t\t\tCandidate's Voter Id : ---> " +voter_id);
                System.out.println();
                System.out.println("\t\t\t\t\tCandidate's Nationality : ---> " +nationality);
                System.out.println();
                System.out.println("\t\t\t\t\tCandidate's Address : ---> " +Address);
                System.out.println();
                System.out.println("\t\t\t\t\tCandidate's Party Name : ---> " +partyname);
                line = br.readLine();
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println("\n==================================================================================================================================\n");
        System.out.println();
    }
    // method to start election;
    void startElection(election e, candidate c, voter v, administrator A) {
        Scanner sc = new Scanner(System.in);
        String s;
        boolean z = false; // Flag to indicate no scheduled election
        System.out.println("\t\t\t\t\tElectronic Voting System\n");

        // Prompt for the election date in dd/mm/yyyy format
        System.out.print("Enter Today's Date (dd/mm/yyyy): ");
        s = sc.nextLine();
        System.out.println();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Disable lenient parsing to enforce a strict format

        // Validate the date format
        try {
            Date electionDate = dateFormat.parse(s);

            int i = 0; // Flag to find election.csv file
            int vx = 0; // Total voters count

            try {
                Path pathToVoterFile = Paths.get("voter.csv");
                BufferedReader brx = Files.newBufferedReader(pathToVoterFile, StandardCharsets.US_ASCII);
                String linex = brx.readLine();

                while (linex != null) {
                    vx++;
                    linex = brx.readLine();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            Path pathToElectionFile = Paths.get("election.csv");

            try (BufferedReader br = Files.newBufferedReader(pathToElectionFile, StandardCharsets.US_ASCII)) {
                String line = br.readLine();
                String[] ptr = line.split(",");

                System.out.println("Election Name: " + ptr[0]);
                
                while (line != null) {
                    ptr = line.split(",");
                    i++;

                    // Compare the parsed date with the election date
                    if (!electionDate.equals(dateFormat.parse(ptr[1]))) {
                        z = true; // No election scheduled for the specified date
                        break;
                    }

                    line = br.readLine();
                }

                if (i == 1) {
                    int tc = 0; // Total candidates count
                    Path pathToCandidateFile = Paths.get("candidate.csv");

                    try (BufferedReader brtc = Files.newBufferedReader(pathToCandidateFile, StandardCharsets.US_ASCII)) {
                        String linetc = brtc.readLine();

                        while (linetc != null) {
                            tc++;
                            linetc = brtc.readLine();
                        }

                        int[] voting = new int[tc];
                        int ele = 1; // Initialize the election loop

                        System.out.println("\t\t\t\t\tElection Name: " + ptr[0]);

                        while (ele != 0 && !z) {
                            String x;
                            System.out.println();
                            System.out.print("\t\t\t\tEnter Voter Id: ");
                            x = sc.next();

                            try {
                                Path pathToVoterFile = Paths.get("voter.csv");
                                BufferedReader br1 = Files.newBufferedReader(pathToVoterFile, StandardCharsets.US_ASCII);
                                String line1 = br1.readLine();
                                int j = 0;

                                while (line1 != null) {
                                    String[] ptr1 = line1.split(",");

                                    if (x.equals(ptr1[2])) {
                                        break;
                                    }
                                    
                                    j++;
                                    line1 = br1.readLine();
                                }

                                if (j == vx) {
                                    System.out.println("Voter with this voter id not found.");
                                } else {
                                    try {
                                        Path pathToCandidatFile = Paths.get("candidate.csv");
                                        BufferedReader brc = Files.newBufferedReader(pathToCandidatFile, StandardCharsets.US_ASCII);
                                        String linec = brc.readLine();
                                        int totalc = 0;

                                        while (linec != null) {
                                            String[] can = linec.split(",");
                                            String name = can[0];
                                            String partyname = can[5];

                                            System.out.println("\n==================================================================================================================================\n");
                                            System.out.println("\t\t\t\t\tCandidate's Name: " + name);
                                            System.out.println("\t\t\t\t\tCandidate's Party Name: " + partyname);
                                            System.out.println("\n==================================================================================================================================\n");

                                            linec = brc.readLine();
                                            totalc++;
                                        }

                                        String choice;
                                        System.out.print("\t\t\t\t\tEnter the name of the candidate you want to vote for: ");
                                        choice = sc.next();

                                        try {
                                            Path pathtoCandidatFile = Paths.get("candidate.csv");
                                            BufferedReader brcc = Files.newBufferedReader(pathtoCandidatFile, StandardCharsets.US_ASCII);
                                            String linecc = brcc.readLine();
                                            int jj = 0;

                                            while (linecc != null) {
                                                String[] ptrcc = linecc.split(",");

                                                if (choice.equals(ptrcc[0])) {
                                                    break;
                                                }

                                                jj++;
                                                linecc = brcc.readLine();
                                            }

                                            if (jj == tc) {
                                                System.out.println("\t\t\t\t\tCandidate not found.");
                                                break;
                                            } else {
                                                voting[jj]++;
                                            }
                                        } catch (IOException ioe) {
                                            ioe.printStackTrace();
                                        }
                                    } catch (IOException ioe) {
                                        ioe.printStackTrace();
                                    }

                                    System.out.println("\t\t\t\t\tYour vote has been counted.");
                                    System.out.println();
                                    System.out.print("\t\t\t\ttEnter 1 to continue voting or 0 to exit: ");
                                    ele = sc.nextInt();

                                    if (ele == 0) {
                                        break;
                                    }
                                }
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }

                        if (ele == 0) {
                            File file = new File("vote1.csv");

                            try {
                                FileWriter fw = new FileWriter("vote1.csv", true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                PrintWriter pw = new PrintWriter(bw);

                                for (int mn = 0; mn < tc; mn++) {
                                    pw.printf("%d\n", voting[mn]);
                                }

                                pw.close();
                                bw.close();
                                fw.close();
                            } catch (IOException f) {
                                f.printStackTrace();
                            }

                            System.out.println("\t\t\t\t\tElection Finished.");
                        }
                    }
                }

                if (z) {
                    System.out.println("\t\t\t\t\tThere is no election scheduled on today.");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (ParseException k) {
            System.out.println("\t\t\t\t\tInvalid date format. Please use dd/mm/yyyy format.");
        }
    }
    // method to show result of election;
    void eleResult(election e, candidate c, voter v, administrator A) {
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.println();
        System.out.print("\t\t\t\t\tEnter Election Name : ---> ");
        s=sc.nextLine();
        int i=1;
        if(i==1){
            int tc=0;
            Path pathToFiletc=Paths.get("candidate.csv");
            try{
                BufferedReader brtc=Files.newBufferedReader(pathToFiletc,StandardCharsets.US_ASCII);
                String linetc=brtc.readLine();
                while(linetc!=null){
                    tc++;
                    linetc=brtc.readLine();
                }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
            int[] tempresult=new int[tc];
            int k=0;
            Path pathToFiletr=Paths.get("vote1.csv");
            try{
                BufferedReader brr = Files.newBufferedReader(pathToFiletr,StandardCharsets.US_ASCII);
                String linee = brr.readLine();
                while(linee!=null){
                    String ss=linee;
                    tempresult[k]=Integer.parseInt(ss); // to parse the string value as a signed decimal value;
                    linee=brr.readLine();
                    k++;
                }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }finally{
            }
            int[] tempr=new int[tc];
            for(int jk=0; jk<tc; jk++) {
                tempr[jk]=tempresult[jk];
            }
            for(int j=0; j<tc; j++) {
                for(int kk=j; kk<tc; kk++){
                    if(tempr[kk]<tempr[j]){
                        int temp=tempr[kk];
                        tempr[kk]=tempr[j];
                        tempr[j]=temp;
                    }
                }
            }
            int j;
            for(j=0; j<tc; j++) {
                if(tempresult[j]==tempr[tc-1]){
                    break;
                }
            }try{
                Path pathToFilec=Paths.get("candidate.csv");
                BufferedReader brc = Files.newBufferedReader(pathToFilec,StandardCharsets.US_ASCII);
                String linec=brc.readLine();
                int totalc=0;
                while(linec!=null){
                    String[] can=linec.split(",");
                    String name = can[0];
                    linec = brc.readLine();
                    if(totalc==j){
                        System.out.println("\n==================================================================================================================================\n");
                        System.out.println("\t\t\t\t\tWinner's Name : ---> " +name);
                        System.out.println("\t\t\t\t\tWinner's total vote : ---> " +tempr[tc-1]);
                        System.out.println("\n==================================================================================================================================\n");
                        break;
                    }
                    totalc++;
                }
               
            }catch(IOException ioe){
                ioe.printStackTrace();
            }finally{
            }
            try{
                Files.deleteIfExists(Paths.get("candidate.csv"));
            }catch(IOException f){
                System.out.println("\t\t\t\t\tInvalid permissions..!");
            }
            try {
                Files.deleteIfExists(Paths.get("election.csv"));
            }catch(IOException f){
                System.out.println("\t\t\t\t\tInvalid permissions.");
            }
            try{
                Files.deleteIfExists(Paths.get("vote1.csv"));
            }catch(IOException f){
                System.out.println("\t\t\t\t\tInvalid permissions.");
            }
        }
    }
}

public class VotingSystem{

    static void Admin(election e, candidate c, voter v, administrator A) {
        Scanner sc = new Scanner(System.in);
        int j=1;
        while(j!=0){
            System.out.println("==================================================================================================================================");
            System.out.println("\t\t\t\t\t*************      ADMINISTRATOR      *********** ");
            System.out.println("==================================================================================================================================\n");
            System.out.println("\t\t\t\t\t* Press ---> 1 To add election");
            System.out.println("\t\t\t\t\t* Press ---> 2 To add candidate");
            System.out.println("\t\t\t\t\t* Press ---> 3 To view candidates list");
            System.out.println("\t\t\t\t\t* Press ---> 4 To view voters list");
            System.out.println("\t\t\t\t\t* Press ---> 5 To start election");
            System.out.println("\t\t\t\t\t* Press ---> 6 To view result of election");
            System.out.println("\t\t\t\t\t* Press ---> 7 TO exit");
            System.out.println("\n==================================================================================================================================\n");
            System.out.print("\t\t\t\t\tEnter your choice : ---> ");
            j=sc.nextInt();
            System.out.println();
            if(j==1){
                A.eadd(e);
            }else if(j==2){
                A.cadd(c);
            }else if(j==3){
                A.candisplay(c);
            }else if(j==4){
                A.votdisplay(v);
            }else if(j==5){
                A.startElection(e,c,v,A);
            }else if(j==6){
                A.eleResult(e,c,v,A);
            }else if(j==7)
            {
                return;
            }

        }
    }

    static void voting(election e, candidate c, voter v, administrator A) {
        Scanner sc=new Scanner(System.in);
        int x=1;
        while(x!=0){
            System.out.println("==================================================================================================================================");
            System.out.println("\t\t\t\t\t*************      VOTER      *********** ");
            System.out.println("==================================================================================================================================\n");
            System.out.println("\t\t\t\t\t* Press ---> 1 To register as voter");
            System.out.println("\t\t\t\t\t* Press ---> 2 To exit");
            System.out.println("\n==================================================================================================================================\n");
            System.out.print("\t\t\t\t\tEnter your choice : ---> ");
            x=sc.nextInt();
            if(x==1){
                System.out.println();
                A.addvoter(v);
            }
            else if(x==2)
            {
                return;
            }
            else
            {
                   System.out.print("\t\t\t\t\tInvalid input");
            }
        }
    }
    public static void main(String[] args) {
        int i=1;
        File file = new File("voter.csv"); // to create voter.csv file at running program;
        try{
            FileWriter fw = new FileWriter("voter.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            // pw.printf("%s,%s,%s\n", new_node.vname, new_node.bday, new_node.vvoter_id);
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException f){
            f.printStackTrace();
        }finally{
        }
        Scanner sc=new Scanner(System.in);
        candidate c=new candidate();
        voter v=new voter();
        election e=new election();
        administrator A=new administrator();
        while(i!=0){
            System.out.println();
            System.out.println("==================================================================================================================================");
            System.out.println("\t\t\t\t\t*************      ONLINE VOTING SYSTEM      *********** ");
            System.out.println("==================================================================================================================================\n");
            System.out.println("\t\t\t\t\t* Press ---> 1 If you are an administrator");
            System.out.println("\t\t\t\t\t* Press ---> 2 If you are a voter");
            System.out.println("\t\t\t\t\t* Press ---> 3 To exit");
            System.out.println("\n==================================================================================================================================\n");
            System.out.print("\t\t\t\t\tEnter your choice : ---> ");
            i=sc.nextInt();
            System.out.println();
            if(i==1){
                System.out.println();
                Admin(e,c,v,A);
            }else if(i==2){
                System.out.println();
                voting(e,c,v,A);
            }
        }
        sc.close();
    }
}
