public class Tower {
	private boolean passRules;

	public Tower() {
		this.passRules = true;
	}

	// Checks if the player's action breaks a tower rule.
	public boolean checkRules(String action) {
		switch (action) {
			case "leaveTowerEarly":
				// 1. It is forbidden to leave the tower before completing the trials.
				passRules = false;
				break;
			case "breakTrialRule":
				// 2. It is forbidden to violate the rules of the trials.
				passRules = false;
				break;
			case "takeBookOutside":
				// 3. It is forbidden to disrespect the library. 
				passRules = false;
				break;
			case "destroyTower":
				// 4. It is forbidden to destroy the tower.
				passRules = false;
				break;
			case "destroyTrial":
				// 5. It is not forbidden to destroy the trials.
				passRules = true;
				break;
			default:
				passRules = true;
		}
		return passRules;
	}

	public boolean summonGuardian() {
		if (!passRules) {
			System.out.println("You broke a rule of the tower!");
			System.out.println("The Tower Guardian appears and attacks you!");
			return true;
		}
		return false;
	}

	public boolean isPassRules() {
		return passRules;
	}
    
	public void setPassRules(boolean passRules) {
		this.passRules = passRules;
	}

	public static void main(String[] args) {
		Tower tower = new Tower();
		tower.checkRules("takeBookOutside");
		tower.summonGuardian();
	}
}