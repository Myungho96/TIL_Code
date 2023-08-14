class Solution {
    int Subscribe = 0;
    int Money = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        //브루트포스로 풀어야할듯..
        permu(0,new int[emoticons.length],users,emoticons);
        answer[0] = Subscribe;
        answer[1] = Money;
        return answer;
    }
    void permu(int cnt, int[] discount, int[][] users, int[] emoticons){
        if(cnt==emoticons.length){
            findAnswer(discount, users, emoticons);
            return;
        }
        for(int i=1;i<=4;i++){
            discount[cnt] = i;
            permu(cnt+1,discount, users, emoticons);
        }
    }
    
    void findAnswer(int[] discount, int[][] users, int[] emoticons){
        int subscribe = 0;
        int money = 0;
        boolean []visited = new boolean[users.length];
        int []userSum = new int[users.length];
        for(int i=0;i<emoticons.length;i++){
            int price = emoticons[i] * (10 - discount[i]) / 10;
            for(int j=0; j<users.length;j++){
                if(!visited[j] && discount[i]*10 >= users[j][0]){
                    userSum[j]+=price;
                    if(userSum[j] >= users[j][1]){
                        subscribe++;
                        visited[j] = true;
                    }
                }
            }
        }
        //money 한번에 해주기
        for(int j=0; j<users.length;j++){
            if(!visited[j]){
                money+=userSum[j];
            }
        }
        if(Subscribe<subscribe){
            Subscribe = subscribe;
            Money = money;
        }else if(Subscribe==subscribe)
            Money = Math.max(money,Money);
    }
}