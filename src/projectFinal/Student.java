package projectFinal;

public class Student{
	String name;
	String st_num;
    String gender;
    String score_java;
    String score_python;
    String score_c;
    //��� ��ΰ� String�� ������ ����� ���� �����ϰ� ��� ��쿡 StringŸ������ �ٷ������ �����Դϴ�.
    public Student() {}
    public Student(String name,String num,String gender,String java,String python,String c) {
    	this.name=name;
    	st_num=""+num;
    	this.gender=gender;
    	score_java=""+java;
    	score_python=""+python;
    	score_c=""+c;
    }
    
    public String getName() {
    	return name;
    }
    public int getSt_num() {
    	return Integer.parseInt(st_num);
    }
    public String getGender() {
    	return gender;
    }
    public int getScore_java() {
    	return Integer.parseInt(score_java);
    }
    public int getScore_python() {
    	return Integer.parseInt(score_python);
    }
    public int getScore_c() {
    	return Integer.parseInt(score_c);
    }
    
    public static int count=6;//�󺧰���+��ư2��
}
