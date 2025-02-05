public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ExecOne());
        t1.start();
        Thread t2 = new Thread(new ExecTwo());
        t2.start();
        Thread t3 = new Thread(new ExecThree());
        t3.start();
    }

    static class ExecOne implements Runnable {
        public void run() {
            for (int i = 1; i < 51; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted!");
                    return;  // stop timer
                }
            }
        }
    }

    static class ExecTwo implements Runnable {
        public void run() {
            for (int i = 1; i < 51; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted!");
                    return;
                }

            }
        }
    }
    static class ExecThree implements Runnable { // Made static
        public void run() {
            for (int i = 1; i < 51; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted!");
                    return;
                }
            }
        }
    }
}

// 1.ในแบบแรกที่ไม่มี sleep() Thread ทั้งสามจะทำงานพร้อมกันซึงจะแย่ง CPU กันใช้ ทำให้ลำดับที่ Print ออกมาจะเป็นแบบสุ่ม ขึ้นอยู่กับ CPU
// 2.ในแบบที่สองที่มีการใช้ sleep() แล้ว จะมีการ delay เกิดขึ้นถ้าค่าเวลาที่ delay ไม่เท่ากันก็ไม่จำเป็นต้องแย่งกัน print ทำให้แต่ละ thread ก็ print ของใครของ
// มัน แต่ถ้าเรา delay พร้อมกันจะทำให้เวลา print ก็จะเป็น ออกมาทีละ 3 ตัว แต่ลำดับจะสุ่ม
// 3.Multithreading คือ การที่เราสั่งการ thread หลายๆอันช่วงเวลาใดช่วงเวลาหนึ่ง ข้อดีก็คือ program จะสามารถทำงานต่อไปได้แม้จะมีสัก thread ทำงานผิดพลาด
// สาเหตุที่บางที output จะมีค่าออกมาแบบสุ่ม จะเป็นก็ต่อเมื่อพอ output จะออกมาพร้อมกัน ซึ่งลำดับจะขึ้นอยู่กับ CPU ที่เราไม่สามารถคาดการณ์ได้นี่เอง