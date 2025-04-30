import org.junit.Test;

public class MainClassTest extends MainClass{
    @Test
    public void testGetLocalNumber() {
        int trueResult = 14;

        int num = this.getLocalNumber();
        if (num != trueResult) {
            System.out.println("Упс..:( Вернулось неверное значение =" + num);
        } else {
            System.out.println("Ура! :) Значение верное =" + num);
        }
    }
    @Test
    public void testGetClassNumber(){
            int trueResult = 45;

            int num = this.getClassNumber();
            if (num > trueResult) {
                System.out.println("Ура! :) Значение верное = " + num + " (>" + trueResult + ")");
            }
            else {
                System.out.println("Упс..:( Вернулось неверное значение = " + num + " (<=" + trueResult + ")");
            }
    }
}
