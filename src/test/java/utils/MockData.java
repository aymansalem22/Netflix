package utils;

import java.util.ArrayList;
import java.util.List;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.json.ActorRest;

public class MockData {

	public static Actor getActor() {
		Actor actor = new Actor();
		actor.setId(1L);
		actor.setName("ali");
		actor.setNationality("ameriacn");
		return actor;
	}
	
	public static ActorRest updategetActor() {
		ActorRest ActorRest1 = new ActorRest();
		ActorRest1.setId(MockData.getActor().getId());
		ActorRest1.setName("alii");
		ActorRest1.setNationality("ameriacn");
		return ActorRest1;
	}
	
	public static ActorRest getActorRest() {
		ActorRest actorRest = new ActorRest();
		actorRest.setId(3L);
		actorRest.setName("aliii");
		actorRest.setNationality("ameriacn");
		return actorRest;
	}

	public static List<Actor> getActorList() {
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(getActor());
		actors.add(getActor());
		return actors;
	}
}
