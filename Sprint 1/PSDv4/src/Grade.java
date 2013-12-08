
public class Grade {

		private char grade;
		private int score, maxScore;
		
		
		public Grade(int maxScore) {
			super();
			this.maxScore = maxScore;
			this.grade = 'U';
		}


		public char getGrade() {
			return grade;
		}


		public void setGrade(char grade) {
			this.grade = grade;
		}


		public int getScore() {
			return score;
		}


		public void setScore(int score) {
			this.score = score;
			if (score >= maxScore * 0.80){
				this.grade = 'A';
			}else if (score >= maxScore * 0.70){
				this.grade = 'B';
			}else if (score >= maxScore * 0.60){
				this.grade = 'C';
			}else if (score >= maxScore * 0.50){
				this.grade = 'D';
			}else{
				this.grade = 'F';
			}
		}
		
		public int getMaxScore() {
			return maxScore;
		}		
}
