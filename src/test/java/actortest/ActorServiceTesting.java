package actortest;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
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
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorParticipant;
import com.everis.d4i.tutorial.json.ActorRest;
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
	public void testGetActorsNullList() throws NetflixException {
		when(actorRepository.findAll()).thenReturn(null);
		assertNull(service.getActors());
	}

	@Test
	public void testGetActors() throws NetflixException {
		when(actorRepository.findAll()).thenReturn(MockData.getActorList());
		assertEquals(2, service.getActors().size());
	}

	@Test
	public void testGetActor() throws NetflixException {
		when(actorRepository.findById(1L)).thenReturn(Optional.of(MockData.getActor()));
		assertEquals("ali", service.getActorById(1L).getName());
	}

	@Test
	public void testCreateActor() throws NetflixException {
		Actor actor = modelMapper.map(MockData.getActorRest(), Actor.class);
		when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor);
		assertEquals("aliii", service.createActor(MockData.getActorRest()).getName());
	}

	@Test
	public void testUpdateActor() throws NetflixException {
		Actor actor = modelMapper.map(MockData.updategetActor(), Actor.class);
		when(actorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(actor));
		when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor);
		assertEquals("alii",
				service.updateActorById(MockData.updategetActor().getId(), MockData.updategetActor()).getName());

	}

	@Test
	public void testDeleteActorId() throws NetflixException {
		when(actorRepository.findById(1L)).thenReturn(Optional.of(MockData.getActor()));
		service.deleteActorById(MockData.getActor().getId());
		verify(actorRepository).findById(MockData.getActor().getId());
	}
//
//	@Test
//	void deleteActorById_throwsExceptionIfIDNotFound() throws NetflixException {
//		assertThatExceptionOfType(NotFoundException.class).isThrownBy(()
//				-> service.deleteActorById(1L))
//				.withMessage("Actor id not found - 1");
//
//	}
//
//	@Test
//	void deleteActorById_deletesActorIfIDFound() throws NetflixException {
//		when(actorRepository.findById(MockData.getActor().getId())).thenReturn(Optional.of(MockData.getActor()));
//		service.deleteActorById(MockData.getActor().getId());
//		verify(actorRepository).delete(MockData.getActor());
//	}
	
	@Test
	 void AddActorToChapters() throws NetflixException{
		when(actorRepository.findById(1L)).thenReturn(Optional.of(MockData.getActor()));
		when(chapterService.findChapterById(MockData.getChapter().getId())).thenReturn((MockData.getChapter()));
		//MockData.getActor().getChapters().add(MockData.getChapter());
	ActorParticipant actorRest = modelMapper.map(MockData.getActor(), ActorParticipant.class);
		 actorRest= service.AddActorToChapter(1L, 1L);
		 actorRepository.save(MockData.getActor());
			assertEquals(1, actorRest.getChapters().size());
//		  when(actorRepository.findById(1L)).thenReturn(Optional.of(MockData.getActor()));
//	        when(chapterService.findChapterById(1L)).thenReturn((MockData.getChapter()));
//	        service.AddActorToChapter(1L, 1L);
//	        actorRepository.save(MockData.getActor());
//	        verify(actorRepository).existsByIdAndChapters_Id(1L, 1L);
	}
	
	
	
	
	
	
	
	
	
	
}
