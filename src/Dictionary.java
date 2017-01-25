public class Dictionary {

    private final String[] words;

    public Dictionary(String[] words) {
        this.words = words;
    }

    public String findMostSimilar(String to) {
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; min != 0 && i < words.length; i++) {
            String word = words[i];
            int distance = LevenshteinDistance(to, word);
            if (distance < min) {
                min = distance;
                index = i;
            }
        }
        return words[index];
    }

    private int LevenshteinDistance(String s, String t) {
        if (s.equals(t))
            return 0;
        if (s.length() == 0)
            return t.length();
        if (t.length() == 0)
            return s.length();

        int[] vo = new int[t.length() + 1];
        int[] v1 = new int[t.length() + 1];


        for (int i = 0; i < vo.length; i++)
            vo[i] = i;
        for (int i = 0; i < s.length(); i++) {
            v1[0] = i + 1;
            for (int j = 0; j < t.length(); j++) {
                int cost = (s.charAt(i) == t.charAt(j)) ? 0 : 1;
                v1[j + 1] = Math.min(v1[j] + 1, Math.min(vo[j + 1] + 1, vo[j] + cost));
            }
            for (int j = 0; j < vo.length; j++)
                vo[j] = v1[j];
        }
        return v1[t.length()];
    }

}
