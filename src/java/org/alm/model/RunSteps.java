package org.alm.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RunSteps extends Entities<RunStep>
{
    public  RunSteps() {
        super();
    }
    public RunSteps(Entities entities) {
        super(entities.entities());
    }
}
