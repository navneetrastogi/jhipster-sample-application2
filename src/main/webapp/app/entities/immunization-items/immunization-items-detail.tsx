import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './immunization-items.reducer';
import { IImmunizationItems } from 'app/shared/model/immunization-items.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IImmunizationItemsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ImmunizationItemsDetail extends React.Component<IImmunizationItemsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { immunizationItemsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ImmunizationItems [<b>{immunizationItemsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="title">Title</span>
            </dt>
            <dd>{immunizationItemsEntity.title}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{immunizationItemsEntity.description}</dd>
            <dt>
              <span id="startAge">Start Age</span>
            </dt>
            <dd>{immunizationItemsEntity.startAge}</dd>
            <dt>
              <span id="endAge">End Age</span>
            </dt>
            <dd>{immunizationItemsEntity.endAge}</dd>
          </dl>
          <Button tag={Link} to="/entity/immunization-items" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/immunization-items/${immunizationItemsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ immunizationItems }: IRootState) => ({
  immunizationItemsEntity: immunizationItems.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ImmunizationItemsDetail);
