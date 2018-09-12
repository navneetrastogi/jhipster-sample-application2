import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './incident-record.reducer';
import { IIncidentRecord } from 'app/shared/model/incident-record.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIncidentRecordDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class IncidentRecordDetail extends React.Component<IIncidentRecordDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { incidentRecordEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            IncidentRecord [<b>{incidentRecordEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="natureOfIncident">Nature Of Incident</span>
            </dt>
            <dd>{incidentRecordEntity.natureOfIncident}</dd>
            <dt>
              <span id="firstAidProvided">First Aid Provided</span>
            </dt>
            <dd>{incidentRecordEntity.firstAidProvided ? 'true' : 'false'}</dd>
            <dt>
              <span id="firstAidNotes">First Aid Notes</span>
            </dt>
            <dd>{incidentRecordEntity.firstAidNotes}</dd>
            <dt>
              <span id="date">Date</span>
            </dt>
            <dd>
              <TextFormat value={incidentRecordEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createdOn">Created On</span>
            </dt>
            <dd>
              <TextFormat value={incidentRecordEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="isAdminOnly">Is Admin Only</span>
            </dt>
            <dd>{incidentRecordEntity.isAdminOnly ? 'true' : 'false'}</dd>
            <dt>
              <span id="notes">Notes</span>
            </dt>
            <dd>{incidentRecordEntity.notes}</dd>
            <dt>
              <span id="students">Students</span>
            </dt>
            <dd>{incidentRecordEntity.students}</dd>
          </dl>
          <Button tag={Link} to="/entity/incident-record" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/incident-record/${incidentRecordEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ incidentRecord }: IRootState) => ({
  incidentRecordEntity: incidentRecord.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IncidentRecordDetail);
