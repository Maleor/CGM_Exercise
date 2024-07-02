package dodardmathieu.cgmexercise.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import dodardmathieu.cgmexercise.error.IResource;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7362106103796851031L;

	public ResourceNotFoundException(IResource resource, String id) {
		super(new StringBuilder("Resource ")
				.append(resource.getResourceName())
				.append(" not found. Id : ")
				.append(id).toString());
	}
}
