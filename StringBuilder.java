public class StringBuilder {
    private int minCapacity;
    private final int EXTRA_CAPACITY = 16;

    public char[] getcArr() {
        return cArr;
    }

    private char[] cArr;

    public StringBuilder() {
        minCapacity = EXTRA_CAPACITY;
        cArr = new char[EXTRA_CAPACITY];
    }

    public StringBuilder(StringBuilder sb) {
        this(sb.capacity());
        String str = sb.toString();

        // TODO: use replace() instead of for loop
        for (int i = 0; i < str.length(); i++) {
            cArr[i] = str.charAt(i);
        }
    }

    public StringBuilder(int capacity) {
        minCapacity = capacity;
        cArr = new char[capacity];
    }

    public StringBuilder(String str) {
        cArr = new char[str.length() + EXTRA_CAPACITY];

        // TODO: use replace() instead of for loop
        // .replace(0, str.length() - 1, str)
        for (int i = 0; i < str.length(); i++) {
            cArr[i] = str.charAt(i);
        }
    }

    public void append(String str) {
        int cArrLen = length();
        if (length() + str.length() > capacity()) {
            char[] cArr2 = new char[cArrLen + str.length() + EXTRA_CAPACITY];
            System.arraycopy(cArr, 0, cArr2, 0, cArrLen);
            cArr = cArr2;

            appendValue(cArrLen, str);
        } else {
            appendValue(cArrLen, str);
        }
    }

    public void append(int num) {
        String strNum = String.valueOf(num);
        int cArrLen = length();

        if (length() + strNum.length() > capacity()) {
            char[] cArr2 = new char[cArrLen + strNum.length() + EXTRA_CAPACITY];
            System.arraycopy(cArr, 0, cArr2, 0, cArrLen);
            cArr = cArr2;

            appendValue(cArrLen, strNum);
        } else {
            appendValue(cArrLen, strNum);
        }
    }

    public void append(char ch) {
        int cArrLen = length();

        if (length() + 1 > capacity()) {
            char[] cArr2 = new char[cArrLen + 1 + EXTRA_CAPACITY];
            System.arraycopy(cArr, 0, cArr2, 0, cArrLen);
            cArr = cArr2;

            appendValue(cArrLen, ch);
        } else {
            appendValue(cArrLen, ch);
        }
    }

    private void appendValue(int cArrLen, String str) {
        for (int i = cArrLen, j = 0; i < cArrLen + str.length(); i++, j++) {
            cArr[i] = str.charAt(j);
        }
    }

    private void appendValue(int cArrLen, char ch) {
        cArr[cArrLen] = ch;
    }

    public int capacity() {
        return cArr.length;
    }

    public int length() {
        if (cArr[cArr.length - 1] != '\u0000')
            return capacity();

        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == '\u0000') {
                return i;
            }
        }
        return -1;
    }

    public void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity <= minCapacity) {
            return;
        }
        char[] cArr2 = new char[cArr.length + EXTRA_CAPACITY];
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        cArr = cArr2;
    }

    public void reverse() {
        int cArrLen = length();
        for (int i = 0; i < cArrLen / 2; i++) {
            char numA = cArr[i];
            char numB = cArr[cArrLen - i - 1];

            cArr[i] = numB;
            cArr[cArrLen - i - 1] = numA;
        }
    }

    public int indexOf(String str, int fromIndex) {
        return indexOf(str, fromIndex, true);
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return indexOf(str, fromIndex, false);
    }

    private int indexOf(String str, int fromIndex, boolean findFirst) {
        int cArrLen = length();
        int result = -1;

        if (fromIndex < 0) {
            return result;
        }
        boolean isFound = false;

        for (int i = fromIndex; i < cArrLen; i++) {
            if (isFound && findFirst) break;

            if (cArr[i] == str.charAt(0)) {
                result = i;
                if (str.length() == 1) {
                    isFound = true;
                    if (findFirst) break;
                }
                  for (int j = 1; j < str.length(); j++) {
                    if (i + j == cArrLen) {
                        result = -1;
                        if (findFirst) break;
                    }
                    if (cArr[i + j] == str.charAt(j)) {
                        if (j == str.length() - 1) {
                            isFound = true;
                        }
                    } else {
                        if (findFirst) break;
                    }
                }
            }
        }
        return result;
    }

    public void setCharAt(int index, char ch) {
        int cArrLen = length();
        if (index < 0) {
            return;
        }
        for (int i = 0; i < cArrLen; i++) {
            if (i == index) {
                cArr[i] = ch;
            }
        }
    }

    public void insert(int offset, String str) {
        int cArrLen = length();
        StringBuilder sb1 = new StringBuilder();

        for (int i = 0; i < cArrLen; i++) {
            if (i == offset) {
                sb1.append(str);
            }
            sb1.append(cArr[i]);
        }
        cArr = sb1.getcArr();
    }

    public void replace(int start, int end, String str) {
        int cArrLen = length();
        if (start < 0 && end > cArrLen && end < start) {
            throw new IllegalArgumentException("Invalid start or end argument!");
        }

        StringBuilder sb1 = new StringBuilder();

        for (int i = 0; i < cArrLen; i++) {
            if (i == start) {
                sb1.append(str);
                i = end;
            } else {
                sb1.append(cArr[i]);
            }
        }
        cArr = sb1.getcArr();
    }

    public void trimToSize() {
        int cArrLen = length();
        char[] cArr2 = new char[cArrLen];
        System.arraycopy(cArr, 0, cArr2, 0, cArrLen);
        cArr = cArr2;
    }

    public String substring(int start) {
        int cArrLen = length();
        if (start < 0 || start > cArrLen) {
            throw new IllegalArgumentException("Invalid start argument!");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = start; i < cArrLen; i++) {
            sb.append(cArr[i]);
        }
        return sb.toString();
    }

    public String substring(int start, int end) {
        int cArrLen = length();
        if (start < 0 || start > end || end > cArrLen ) {
            throw new IllegalArgumentException("Invalid start or end arguments!");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = start; i <= end; i++) {
            sb.append(cArr[i]);
        }
        return sb.toString();
    }

    public void delete(int start, int end) {
        int cArrLen = length();
        if (start < 0 || start > end || end > cArrLen ) {
            throw new IllegalArgumentException("Invalid start or end arguments!");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cArrLen; i++) {
            if (i == start) {
                i = end;
            } else {
                sb.append(cArr[i]);
            }
        }
        cArr = sb.getcArr();
    }

    public void delete(int start) {
        int cArrLen = length();
        if (start < 0 || start > cArrLen ) {
            throw new IllegalArgumentException("Invalid start argument!");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cArrLen; i++) {
            if (i == start) {
                break;
            } else {
                sb.append(cArr[i]);
            }
        }
        cArr = sb.getcArr();
    }


    @Override
    public String toString() {
        int cArrLen = length();
        String str = "";
        for (int i = 0; i < cArrLen; i++) {
            str += String.valueOf(cArr[i]);
        }
        return str;
    }
}
