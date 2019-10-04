import java.util.*;
public class Class {
	public static void main(String[] args) {
		Student student1 = new Student("Josh","Moshe",11,2019,"Precalc-5","Goldman","High",70);
		student1.addClass("US History-4","Cullen",98);
		Student student2 = new Student("Kelly","Smith",6,2016,"Science-3","Doe","Middle",81);
		Student student3 = new Student("George","Pop",12,2018,"Precalc-4","Goldman","High",65);
		student3.addClass(11,2017,"US History-4","Cullen",98);
		student3.addClass(4,2008,"4th Grade","Smith","Elementary",65);
		Student student4 = new Student("Heather","Wilson",7,2016,"Science-2","Doe","Middle",99);
		student4.addClass(0,2010,"Kindergarten","Smith","Elementary",89);
		Student student5 = new Student("Tom","Brokaw",11,2019,"Precalc-5","Liasi","High",90);
		Student student6 = new Student("Isabelle","Frank",10,2018,"Precalc-4","Goldman","High",83);
		student6.addClass(9,2017,"Algebra-7","Goldman",95);
		student6.addClass(4,2012,"4th Grade","Smith","Elementary",100);
		Student[] school = new Student[] {student1,student2,student3,student4,student5,student6};
		System.out.println(teacherBySchool(school,"High"));
		System.out.println(teacherBySchool(school,"Middle"));
		System.out.println(HSDepartments(school));
		System.out.println(mathCourses(school));
		student1.displayCurrClassInfo();
		student4.displayAllClassInfo();
		student4.displayClassesBySchool("Elementary");
		System.out.println(coursesCurrentlyTaughtBy("Goldman",school));
		System.out.println(yearAvg("4",school));
		System.out.println(highestInCourse("Precalc",school));
		System.out.println(currCourseAvg("Precalc",school));
		System.out.println(currCourseTeacherAvg("Goldman","Precalc",school));
	}
	public static String teacherBySchool(Student[] kids, String school) {
		TreeSet<String> ts = new TreeSet<String>();
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(y[4].equals(school)) {
					ts.add(y[3]);
				}
			}
		}
		
		return ts.toString();
	}
	public static String HSDepartments(Student[] kids) {
		TreeSet<String> ts = new TreeSet<String>();
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(y[4].equals("High")) {
					if(y[2].indexOf("Precalc")>-1||y[2].indexOf("Algebra")>-1) {
						ts.add("Mathematics");
					}else if(y[2].indexOf("US History")>0) {
						ts.add("Social Studies");
					}
				}
			}
		}
		return ts.toString();
	}
	public static String mathCourses(Student[] kids) {
		TreeSet<String> ts = new TreeSet<String>();
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(y[2].indexOf("Precalc")>-1||y[2].indexOf("Algebra")>-1) {
					ts.add(y[2]);
				}
			}
		}
		return ts.toString();
	}
	public static String coursesCurrentlyTaughtBy(String teacher, Student[] kids) {
		TreeSet<String> ts = new TreeSet<String>();
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(y[3].equals(teacher)&&y[0].equals("2019")) {
					ts.add(y[2]);
				}
			}
		}
		return ts.toString();
	}
	public static String yearAvg(String year, Student[] kids) {
		int c = 0;
		int sum = 0;
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(y[0].equals(year)) {
					c++;
					sum += Integer.valueOf(y[5]);
				}
			}
		}
		return String.valueOf(sum/c);
	}
	public static int highestInCourse(String course, Student[] kids) {
		int max=0;
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(Integer.valueOf(y[5])>max&&y[2].indexOf(course)>-1) {
					max = Integer.valueOf(y[5]);
				}
			}
		}
		return max;
	}
	public static int currCourseAvg(String course, Student[] kids) {
		int c = 0;
		int sum = 0;
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(y[1].equals("2019")&&y[2].indexOf(course)>-1) {
					c++;
					sum += Integer.valueOf(y[5]);
				}
			}
		}
		return sum/c;
	}
	public static int currCourseTeacherAvg(String teacher,String course, Student[] kids) {
		int c = 0;
		int sum = 0;
		for(Student x: kids) {
			ArrayList<String[]> temp = x.getClasses();
			for(String[] y: temp) {
				if(y[3].equals(teacher)&&y[2].indexOf(course)>-1) {
					c++;
					sum += Integer.valueOf(y[5]);
				}
			}
		}
		return sum/c;
	}
}
class Student{
	private ArrayList<String[]> classes;
	private String first, last;
	public Student(String f, String l, int year, int date, String classname, String teacher, String school, int grade) {
		first = f;
		last = l;
		classes = new ArrayList<String[]>();
		classes.add(new String[] {Integer.toString(year),Integer.toString(date),classname,teacher,school,Integer.toString(grade)});
	}
	public void addClass(int year, int date, String classname, String teacher, String school, int grade) {
		classes.add(new String[] {Integer.toString(year),Integer.toString(date),classname,teacher,school,Integer.toString(grade)});
	}
	public void addClass(int year, int date, String classname, String teacher, int grade) {
		classes.add(new String[] {Integer.toString(year),Integer.toString(date),classname,teacher,classes.get(classes.size()-1)[4],Integer.toString(grade)});
	} 
	public void addClass(String classname, String teacher,int grade) {
		classes.add(new String[] {classes.get(classes.size()-1)[0],classes.get(classes.size()-1)[1],classname,teacher,classes.get(classes.size()-1)[3],Integer.toString(grade)});
	}
	public String getFirst() {return first;}
	public String getLast() {return last;}
	public ArrayList<String[]> getClasses() {return classes;}
	public void displayCurrClassInfo() {
		ArrayList<String[]> cList = new ArrayList<String[]>();
		for(String[] x:classes) {
			if(x[1].equals("2019")) {
				if(cList.size()>0) {
					int c = 0;
					while(c<cList.size()&&cList.get(c)[0].compareTo(x[2])<0){
						c++;
					}
					cList.add(c-1,new String[] {x[2],x[5]});
				}else {
					cList.add(new String[] {x[2],x[5]});
				}
			}
		}
		for(String[] x:cList) {
			System.out.print("\n"+ x[0] + ": " + x[1]);
		}
	}
	public void displayAllClassInfo() {
		ArrayList<String[]> cList = new ArrayList<String[]>();
		for(String[] x:classes) {
			
			if(cList.size()>0) {
				int c = 0;
				while(c<cList.size()&&cList.get(c)[2].compareTo(x[0])<0){
					c++;
				}
				cList.add(c,new String[] {x[2],x[5],x[0],x[1]});
			}else {
				cList.add(new String[] {x[2],x[5],x[0],x[1]});
			}
			
		}
		for(String[] x:cList) {
			System.out.print("\n"+ x[0] + ": " + x[1] +" Year: "+ x[2] +" Date: " + x[3]);
		}
	}
	public void displayClassesBySchool(String school) {
		ArrayList<String[]> cList = new ArrayList<String[]>();
		for(String[] x:classes) {
			if(x[4].equals(school)) {
				if(cList.size()>0) {
					int c = 0;
					while(c<classes.size()&&cList.get(c)[1].compareTo(x[4])<0){
						c++;
					}
					cList.add(new String[] {x[2],x[4]});
				}else {
					cList.add(new String[] {x[2],x[4]});
				}
			}
		}
		for(String[] x:cList) {
			System.out.print("\n"+ x[0]);
		}
	}
}