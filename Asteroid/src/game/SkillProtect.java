package game;

public class SkillProtect {
	private int cooldown;
	private int curCooldown;
	private int curTime;
	private int duration;
	private boolean active;
	private Animation animation;
	
	
	SkillProtect(){
		cooldown = 2000;
		duration = 500;
		active = false;
		
		animation = new Animation();
		animation.add(new Sprite("protection/protection_1.png"),2);
		animation.add(new Sprite("protection/protection_2.png"),2);
		animation.add(new Sprite("protection/protection_3.png"),2);
		animation.add(new Sprite("protection/protection_4.png"),2);
		animation.add(new Sprite("protection/protection_5.png"),2);
		animation.add(new Sprite("protection/protection_6.png"),2);
		animation.add(new Sprite("protection/protection_7.png"),2);
		animation.add(new Sprite("protection/protection_8.png"),2);
		animation.add(new Sprite("protection/protection_9.png"),2);
		animation.add(new Sprite("protection/protection_10.png"),2);
		animation.add(new Sprite("protection/protection_9.png"),2);
		animation.add(new Sprite("protection/protection_8.png"),2);
		animation.add(new Sprite("protection/protection_7.png"),2);
		animation.add(new Sprite("protection/protection_6.png"),2);
		animation.add(new Sprite("protection/protection_5.png"),2);
		animation.add(new Sprite("protection/protection_4.png"),2);
		animation.add(new Sprite("protection/protection_3.png"),2);
		animation.add(new Sprite("protection/protection_2.png"),2);
	}
	public void reset(){
		curCooldown = 0;
		active = false;
	}
	public void update(){
		if(active){
			curTime++;
			if(curTime>duration){
				active = false;
				curTime = 0;
				curCooldown = cooldown;
			}
		}
	
		if(curCooldown>0){
			curCooldown--;
		}
	}
	public void activate(){
		if(curCooldown==0 && !active){
			active = true;
			curTime = 0;
		}
	}
	public boolean isActive(){
		return active;
	}
	public Sprite getImage(){
		return animation.getSprite();
	}
}
