package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.services.SubforumService;

public class SubforumServiceImpl implements SubforumService {

	private Map<String, Subforum> subforums = MyDatabase.getSubforums();

	@Override
	public Subforum findOne(String id) {
		if (subforums.containsKey(id)) {
			return subforums.get(id);
		}
		return null;
	}

	@Override
	public List<Subforum> findAll() {
		return new ArrayList<>(subforums.values());
	}

	@Override
	public Subforum create(Subforum entity) {
		if (subforums.containsKey(entity.getTitle())) {
			return null;
		}
		
		subforums.put(entity.getTitle(), entity);
		return entity;
	}

	@Override
	public void remove(String id) {
		Subforum sf = subforums.get(id);
		
		if (sf != null) {
			subforums.remove(id);
		}
	}

}
