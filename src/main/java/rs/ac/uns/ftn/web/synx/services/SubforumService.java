package rs.ac.uns.ftn.web.synx.services;

import rs.ac.uns.ftn.web.synx.model.Subforum;

public interface SubforumService extends CrudService<Subforum, String> {

	Subforum update(String id, Subforum newSubforum);

}
