package accountingApp.service;

import accountingApp.entity.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.repository.EventsRepository;

import java.util.List;

@Service
public class EventsService {
	@Autowired
	EventsRepository eventsRepository;

	public List<Events> findAllEvents() {
		return eventsRepository.findAll();
	}

	public void addNewEvent(Events events) {
		eventsRepository.save(events);
	}

	public void deleteRecreantsAegersById(int id) {
		eventsRepository.deleteById(id);
	}

	public void updateRecreantsAegers(Events events) {
		eventsRepository.save(events);
	}

//	public List<Events> getRecreantsAegersById(int id) {
//		return eventsRepository.findById(id);
//	}
}
