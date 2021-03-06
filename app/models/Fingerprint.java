package models;

import java.util.List;
import java.util.ArrayList;

import models.AccessPoint;
import models.APFingerprint;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * Fingerprint.
 *
 * @author: Jan Schmalfuß
 */
@Entity
public class Fingerprint extends Model {

	@Id
	public Long id;

	@Required
	public float x;
	public float y;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<APFingerprint> accesspoints;	

	/**
	 * Constructor.
	 */
	public Fingerprint(float x, float y) {		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Static methods.
	 */

	/**
	 * Helper for database operations, Ebean.
	 */
	public static Finder<Long,Fingerprint> find = new Finder(Long.class, Fingerprint.class);

	/**
	 * Get all stored objects.
	 */
	public static List<Fingerprint> all() {
		return find.all();
	}

	/**
	 * Store a new object in Ebean.
	 */
	public static void create(Fingerprint fp) {
		fp.save();
	}

	/**
	 * Delete a single object.
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	/**
	 * Delete all objects.
	 */
	public static void deleteAll() {
		List<Fingerprint> list = find.all();
		for (Fingerprint item: list) {
			Fingerprint.delete(item.id);
		}
	}

	
	/**
	 * Update. @return updated Fingerprint
	 * 
	 * clear() doesn't work
	 */
	public static Fingerprint update(Fingerprint fp, float x, float y, List<APFingerprint> aps) {
		fp.accesspoints.clear();
		fp.accesspoints.addAll(aps);
		fp.x = x;
		fp.y = y;
		fp.update();
		return fp;
	}
	
	public static Fingerprint get(Long id) {
		return find.ref(id);
	}
}
