package com.myapp.struts.admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;



public class ReadLog {
	static final String DATE = "dateTime";
	static final String ITEM = "userinfo";
	static final String MODE = "library_id";
	static final String UNIT = "url";
	static final String CURRENT = "userid";
	static final String INTERACTIVE = "role";
        static final String sublibrary = "sublibrary_id";

	@SuppressWarnings({ "unchecked", "null" })
	public List<Item> readConfig(String configFile) {
		List<Item> items = new ArrayList<Item>();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			Item item = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have a item element we create a new item
					if (startElement.getName().getLocalPart() == (ITEM)) {
						item = new Item();
						// We read the attributes from this tag and add the date
						// attribute to our object
						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(ITEM)) {
								//item.setDateTime(attribute.getValue());
							}

						}
					}


					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(DATE)) {
							event = eventReader.nextEvent();
							item.setDateTime(event.asCharacters().getData());
							continue;
						}
					}
                                        if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals("username")) {
							event = eventReader.nextEvent();
							item.setUsername(event.asCharacters().getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(MODE)) {
							event = eventReader.nextEvent();
							item.setLibrary_id(event.asCharacters().getData());
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(UNIT)) {
						event = eventReader.nextEvent();
						item.setUrl(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(CURRENT)) {
						event = eventReader.nextEvent();
						item.setUserid(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(INTERACTIVE)) {
						event = eventReader.nextEvent();
						item.setRole(event.asCharacters().getData());
						continue;
					}
                                        if (event.asStartElement().getName().getLocalPart()
							.equals(sublibrary)) {
						event = eventReader.nextEvent();
						item.setSublibrary_id(event.asCharacters().getData());
						continue;
					}
				}
				// If we reach the end of an item element we add it to the list
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (ITEM)) {
						items.add(item);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return items;
	}

}


