package br.com.master.util;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.master.entities.BaseEntity;

@FacesConverter("entityConverter")
public class ClienteConverter implements Converter {

    public Object getAsObject(FacesContext ctx, UIComponent component,
	    String value) {
	if (value != null) {
	    return this.getAttributesFrom(component).get(value);
	}
	return null;
    }

    public String getAsString(FacesContext ctx, UIComponent component,
	    Object value) {

	if (value != null && !"".equals(value)) {
	    BaseEntity entity = (BaseEntity) value;
	    // adiciona item como atributo do componente
	    this.addAttribute(component, entity);

	    Long codigo = entity.getId();
	    if (codigo != null) {
		return String.valueOf(codigo);
	    }
	}
	return null;
    }

    protected void addAttribute(UIComponent component, BaseEntity o) {

	if (o.getId() != null) {
	    String key = o.getId().toString();
	    this.getAttributesFrom(component).put(key, o);
	}
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
	return component.getAttributes();
    }

}
