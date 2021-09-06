package actortest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.repositories.ActorRepositoy;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.services.impl.ActorServiceImp;

import utils.MockData;

@RunWith(MockitoJUnitRunner.class)
public class ActorServiceTesting {

	@Mock
	private ActorRepositoy actorRepository;

	@Mock
	private ChapterService chapterService;

	@InjectMocks
	ActorServiceImp service;
	
	private ModelMapper modelMapper = new ModelMapper();

	
	@Test
	public void testGetActors() throws NetflixException {
		when(actorRepository.findAll()).thenReturn(MockData.getActorList());
		assertEquals(2, service.getActors().size());
	}
	
	@Test
	public void testGetActor()throws NetflixException {
		when(actorRepository.findById(1L)).thenReturn(Optional.of(MockData.getActor()));
		assertEquals("ali", service.getActorById(1L).getName());
	}
	
	@Test
	public void testCreateActor()throws NetflixException {
		Actor actor = modelMapper.map(MockData.getActorRest(), Actor.class);
		when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor);
		assertEquals("aliii", service.createActor(MockData.getActorRest()).getName());
	}
	
	@Test
	public void testUpdateActor()throws NetflixException {
		
           



		
	}
	
	
}






