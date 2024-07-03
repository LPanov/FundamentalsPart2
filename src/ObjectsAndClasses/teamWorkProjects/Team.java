package ObjectsAndClasses.teamWorkProjects;

import java.util.*;

public class Team {
    private String name;
    private String creator;
    private List<String> members;

    public Team(String name, String creator) {
        this.name = name;
        members = new ArrayList<>();
        this.creator = creator;
        members.add(creator);
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public List<String> getMembers() {
        return members;
    }

    public static void addUser(String user, Team team) {
        if(isMember(user, team)){
            System.out.println("Member "+user+" cannot join team "+team.getName()+"!");
        } else {
            team.getMembers().add(user);
        }
    }
    private String printMembers() {
        String allMembers = "";
        members.subList(1, members.size()).sort(null);
        for (String member:this.members) {
            if(member.equals(members.get(0))) {
                allMembers += "- " + member + "\n";
            }
            else if (member.equals(members.get(members.size()-1))){
                allMembers += "-- " + member;
            }
            else {
                allMembers += "-- " + member + "\n";
            }

        }
        return  allMembers;
    }
    @Override
    public String toString() {
        return name + '\n' +
                printMembers();
    }

    public static boolean isMember(String user, Team team) {
        for (String member : team.getMembers()) {
            if (user.equals(member)) return true;
        }
        return false;
    }
    public static boolean existMember(String user, List<Team> teams) {
        for (Team team : teams) {
            if (isMember(user, team)) return true;
        }
        return false;
    }
    public static boolean existTeam(String t, List<Team> teams) {
        for (Team team : teams) {
            if (team.getName().equals(t)) return true;
        }
        return false;
    }
    public static Team getTeam (String t, List<Team> teams) {
        for (Team team : teams) {
            if (team.getName().equals(t)) return team;
        }
        return null;
    }

    public static boolean isCreator(String creator, List<Team> teams) {
        for (Team team : teams) {
            if (team.getCreator().equals(creator)) return true;
        }
        return false;
    }

    public int countMembers() {
        return members.size();
   }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        List<Team> teams = new ArrayList<>();

        while(true) {
            String s = sc.nextLine();
            if (s.equals("end of assignment")) break;

            if (s.contains("->")) {
                String[] token = s.split("->");
                if (Team.existTeam(token[1], teams) && !Team.existMember(token[0], teams)) {
                    Team.addUser(token[0], Team.getTeam(token[1], teams));
                }
                else if (!Team.existTeam(token[1], teams)) {
                    System.out.println("Team "+token[1]+" does not exist!");
                }
                else if (Team.existMember(token[0], teams)) {
                    System.out.println("Member "+token[0]+" cannot join team "+token[1]+"!");
                }
            }
            else if (s.contains("-")) {
                String[] token = s.split("-");
                if (Team.existTeam(token[1], teams)) {
                    System.out.println("Team " + token[1] + " was already created!");
                }
                else if (Team.isCreator(token[0], teams)){
                    System.out.println(token[0]+" cannot create another team!");
                } else {
                    System.out.println("Team " + token[1] + " has been created by " + token[0] + "!");
                    Team team = new Team(token[1], token[0]);
                    teams.add(team);
                }
            }
        }
        teams.sort(Comparator.comparing(Team::countMembers, Comparator.reverseOrder()).thenComparing(Team::getName));

        List<Team> disbanded = new ArrayList<>();
        for (Team team : teams) {
            if (team.getMembers().size() == 1) {
                disbanded.add(team);
                teams.set(teams.indexOf(team), null);
            }
        }
        int countNull = 0;
        for (Team team : teams) {
            if(team == null) countNull++;
        }
        for (int i = 0; i < countNull; i++) {
            teams.remove(null);
        }

        for (Team team : teams) {
            System.out.println(team);
        }
        disbanded.sort(Comparator.comparing(Team::getName));
        System.out.println("Teams to disband:");
        for (Team team : disbanded) {
            System.out.println(team.getName());
        }

    }
}
