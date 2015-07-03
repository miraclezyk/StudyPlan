package gefp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements Serializable,UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	String username;

	String password;

	String firstName;

	String lastName;

	String cin;

	String email;
	
	String accessKey;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	Set<String> roles;

	boolean enabled;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "user_checkpoints", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "checkpoint_id"))
	Set<Checkpoint> checkpoints;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "major_id")
	Department major;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "plan_id")
	Plan plan;

	public User() {
		enabled = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(Set<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public Department getMajor() {
		return major;
	}

	public void setMajor(Department major) {
		this.major = major;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities()
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for( String role : roles )
            authorities.add( new SimpleGrantedAuthority( role ) );
        return authorities;
    }

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	public boolean isAdmin()
    {
        return roles.contains( "ROLE_ADMIN" );
    }
	
	public boolean isStudent()
    {
        return roles.contains( "ROLE_STUDENT" );
    }

	public boolean isAdvisor()
    {
        return roles.contains( "ROLE_ADVISOR" );
    }

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

}
