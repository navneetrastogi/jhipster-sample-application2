import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './incident-record.reducer';
import { IIncidentRecord } from 'app/shared/model/incident-record.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IIncidentRecordUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IIncidentRecordUpdateState {
  isNew: boolean;
}

export class IncidentRecordUpdate extends React.Component<IIncidentRecordUpdateProps, IIncidentRecordUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { incidentRecordEntity } = this.props;
      const entity = {
        ...incidentRecordEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/incident-record');
  };

  render() {
    const { incidentRecordEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplication2App.incidentRecord.home.createOrEditLabel">Create or edit a IncidentRecord</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : incidentRecordEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="incident-record-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="natureOfIncidentLabel" for="natureOfIncident">
                    Nature Of Incident
                  </Label>
                  <AvField id="incident-record-natureOfIncident" type="text" name="natureOfIncident" />
                </AvGroup>
                <AvGroup>
                  <Label id="firstAidProvidedLabel" check>
                    <AvInput id="incident-record-firstAidProvided" type="checkbox" className="form-control" name="firstAidProvided" />
                    First Aid Provided
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="firstAidNotesLabel" for="firstAidNotes">
                    First Aid Notes
                  </Label>
                  <AvField id="incident-record-firstAidNotes" type="text" name="firstAidNotes" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateLabel" for="date">
                    Date
                  </Label>
                  <AvField id="incident-record-date" type="date" className="form-control" name="date" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdOnLabel" for="createdOn">
                    Created On
                  </Label>
                  <AvField id="incident-record-createdOn" type="date" className="form-control" name="createdOn" />
                </AvGroup>
                <AvGroup>
                  <Label id="isAdminOnlyLabel" check>
                    <AvInput id="incident-record-isAdminOnly" type="checkbox" className="form-control" name="isAdminOnly" />
                    Is Admin Only
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="notesLabel" for="notes">
                    Notes
                  </Label>
                  <AvField id="incident-record-notes" type="text" name="notes" />
                </AvGroup>
                <AvGroup>
                  <Label id="studentsLabel" for="students">
                    Students
                  </Label>
                  <AvField id="incident-record-students" type="text" name="students" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/incident-record" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  incidentRecordEntity: storeState.incidentRecord.entity,
  loading: storeState.incidentRecord.loading,
  updating: storeState.incidentRecord.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IncidentRecordUpdate);
