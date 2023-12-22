public class PasswordList {
    //Liste(Array)

    Account[] list = new Account[10];

    //Methode "add"
    public void add(Account account){
        boolean isadd = false;
        for(int i = 0; i < list.length; i++){
            if(list[i] == null){
                list[i] = account;
                isadd = true;
                break;
            }
        }
        if(!isadd) {
            Account[] newList = new Account[list.length + 10];
            expandList(list, newList);
            newList[list.length] = account;

            list = newList;
        }
    }

    //Methode "remove"
    public void remove(String platform, String name){
        for(int i = 0; i < list.length; i++){
            if(list[i].getPlatform().equalsIgnoreCase(platform) && list[i].getName().equalsIgnoreCase(name)){
                list[i] = null;

                //alle nachfolgende elemente nach links schieben um lücke zu vermeiden
                for(int j = i; j < list.length - 1; j++){
                    list[j] = list[j+1];
                }

                list[list.length-1] = null;

                break;
            }
        }
    }


    //Methode "expandList"
    public void expandList(Account[] a, Account[] b){
        int counter = 0;
        for (Account account : a) {
            if (account != null) {
                b[counter] = account;
                counter++;
            }
        }
    }
}
