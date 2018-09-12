import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './immunization-items.reducer';
import { IImmunizationItems } from 'app/shared/model/immunization-items.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IImmunizationItemsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ImmunizationItems extends React.Component<IImmunizationItemsProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { immunizationItemsList, match } = this.props;
    return (
      <div>
        <h2 id="immunization-items-heading">
          Immunization Items
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Immunization Items
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Start Age</th>
                <th>End Age</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {immunizationItemsList.map((immunizationItems, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${immunizationItems.id}`} color="link" size="sm">
                      {immunizationItems.id}
                    </Button>
                  </td>
                  <td>{immunizationItems.title}</td>
                  <td>{immunizationItems.description}</td>
                  <td>{immunizationItems.startAge}</td>
                  <td>{immunizationItems.endAge}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${immunizationItems.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${immunizationItems.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${immunizationItems.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ immunizationItems }: IRootState) => ({
  immunizationItemsList: immunizationItems.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ImmunizationItems);
